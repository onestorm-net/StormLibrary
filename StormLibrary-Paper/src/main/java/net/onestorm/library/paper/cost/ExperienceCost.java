package net.onestorm.library.paper.cost;

import net.onestorm.library.cost.Refundable;
import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.entity.Player;

public class ExperienceCost extends PaperCost {

    private static final String COST_NAME = "experience";

    private final int experience;

    public ExperienceCost(int experience) {
        this.experience = experience;
    }

    @Override
    public String getName() {
        return COST_NAME;
    }


    @Override
    public boolean canPay(PlayerContext context) {
        Player player = context.getPlayer();
        return getTotalExperience(player) >= experience;
    }

    @Override
    public Refundable pay(PlayerContext context) {
        Player player = context.getPlayer();
        takeExperience(player, experience);

        return new PaperRefundable() {
            @Override
            public void refund(PlayerContext context) {
                context.getPlayer().giveExp(experience);
            }
        };
    }

    private int getTotalExperience(Player player) {
        int level = player.getLevel();
        int experience = Math.round(getExperienceAtLevel(level) * player.getExp());
        while (level > 0) {
            level--;
            experience += getExperienceAtLevel(level);
        }
        if (experience < 0) { // overflow guard
            return Integer.MAX_VALUE;
        }
        return experience;
    }

    private void setTotalExperience(Player player, int experience) {
        if (experience < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }

        player.setExp(0f); // progress
        player.setLevel(0);
        player.setTotalExperience(0);
        player.giveExp(experience);
    }

    private int getExperienceAtLevel(int level) {
        if (level <= 15) {
            return (2 * level) + 7;
        }
        if (level <= 30) {
            return (5 * level) - 38;
        }
        return (9 * level) - 158;

    }

    private void takeExperience(Player player, int amount) {
        int current = getTotalExperience(player);
        if (current < amount) {
            throw new IllegalArgumentException("Not enough experience, check canPay first!");
        }
        setTotalExperience(player, current - amount);
    }
}
