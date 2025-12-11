package net.onestorm.library.paper.cost;

import net.onestorm.library.cost.Refundable;
import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;

public class ItemStackCost extends PaperCost {

    private static final String COST_NAME = "item-stack";

    private final ItemStack itemStack;
    private final int amount;

    public ItemStackCost(ItemStack itemStack, int amount) {
        this.itemStack = itemStack;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return COST_NAME;
    }

    @Override
    public boolean canPay(PlayerContext context) {
        Player player = context.getPlayer();
        PlayerInventory inventory = player.getInventory();
        int needed = amount;
        for (ItemStack item : inventory.getStorageContents()) {
            if (item == null || !item.isSimilar(itemStack)) {
                continue;
            }
            needed -= item.getAmount();
            if (needed <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Refundable pay(PlayerContext context) {
        if (!Bukkit.isPrimaryThread()) {
            throw new IllegalStateException("ItemStackCost.pay(PlayerContext) must be called from the primary server thread");
        }
        Player player = context.getPlayer();
        PlayerInventory inventory = player.getInventory();
        List<ItemStack> refundList = new ArrayList<>();

        int needed = amount;
        ItemStack[] contents = inventory.getStorageContents();

        for (int slot = 0; slot < contents.length && needed > 0; slot++) {
            ItemStack item = contents[slot];
            if (item == null || !item.isSimilar(itemStack)) {
                continue;
            }

            int amount = item.getAmount();
            int take = Math.min(amount, needed);
            needed -= take;

            ItemStack refund = item.clone();
            refund.setAmount(take);
            refundList.add(refund);

            if (amount == take) {
                contents[slot] = null;
            } else {
                ItemStack remainder = item.clone();
                remainder.setAmount(amount - take);
                contents[slot] = remainder;
            }
        }

        if (needed > 0) {
            throw new IllegalStateException("Insufficient items for " + player.getName());
        }

        inventory.setStorageContents(contents);
        return new ItemStackRefundable(refundList);
    }


}
