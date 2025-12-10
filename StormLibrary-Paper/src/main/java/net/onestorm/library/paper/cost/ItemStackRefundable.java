package net.onestorm.library.paper.cost;


import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public class ItemStackRefundable extends PaperRefundable {

    private final List<ItemStack> itemStackList;

    public ItemStackRefundable(List<ItemStack> itemStackList) {
        this.itemStackList = itemStackList;
    }

    @Override
    public void refund(PlayerContext context) {

        // todo maybe add a scheduler, to run this on the next available tick?

        Player player = context.getPlayer();
        PlayerInventory inventory = player.getInventory();

        for (ItemStack itemStack : itemStackList) {
            inventory.addItem(itemStack);
        }

        player.updateInventory();
    }
}
