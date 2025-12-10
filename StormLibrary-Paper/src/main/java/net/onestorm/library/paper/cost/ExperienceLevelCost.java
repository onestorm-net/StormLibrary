package net.onestorm.library.paper.cost;

import net.onestorm.library.cost.Refundable;
import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.entity.Player;

public class ExperienceLevelCost extends PaperCost {

    private static final String COST_NAME = "experience-level";

    private final int level;

    public ExperienceLevelCost(int level) {
        this.level = level;
    }

    @Override
    public String getName() {
        return COST_NAME;
    }

    @Override
    public boolean canPay(PlayerContext context) {
        Player player = context.getPlayer();
        return player.getLevel() >= level;
    }

    @Override
    public Refundable pay(PlayerContext context) {
        Player player = context.getPlayer();

        if (player.getLevel() < level) {
            throw new IllegalStateException("Insufficient experience levels for " + player.getName());
        }

        player.giveExpLevels(-level);

        return new PaperRefundable() {
            @Override
            public void refund(PlayerContext context) {
                context.getPlayer().giveExpLevels(level);
            }
        };
    }
}
