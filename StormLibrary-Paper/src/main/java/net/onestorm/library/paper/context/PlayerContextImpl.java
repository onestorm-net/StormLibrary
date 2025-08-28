package net.onestorm.library.paper.context;

import net.onestorm.library.paper.user.PaperUser;
import net.onestorm.library.user.User;
import org.bukkit.entity.Player;

public class PlayerContextImpl implements PlayerContext {

    private final Player player;

    public PlayerContextImpl(Player player) {
        this.player = player;
    }

    @Override
    public User getUser() {
        return new PaperUser(player);
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
