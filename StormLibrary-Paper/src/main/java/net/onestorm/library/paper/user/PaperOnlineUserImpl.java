package net.onestorm.library.paper.user;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PaperOnlineUserImpl implements PaperOnlineUser {

    private final Player player;

    public PaperOnlineUserImpl(Player player) {
        this.player = player;
    }

    @Override
    public UUID getUuid() {
        return player.getUniqueId();
    }

    @Override
    public String getUsername() {
        return player.getName();
    }

    @Override
    public void sendMessage(Component message) {
        player.sendMessage(message);
    }

    @Override
    public boolean hasPermission(String node) {
        return player.hasPermission(node);
    }

    @Override
    public Player asPlayer() {
        return player;
    }

}
