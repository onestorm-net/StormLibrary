package net.onestorm.library.paper.action;

import net.kyori.adventure.sound.Sound;
import net.onestorm.library.paper.user.PaperUser;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PlaySoundAction extends PaperAction {

    private final Server server;
    private final String scope; // player, world, location, server
    private final Sound sound;


    public PlaySoundAction(Server server, String scope, Sound sound) {
        this.server = server;
        this.scope = scope;
        this.sound = sound;
    }

    @Override
    void execute(PaperUser user) {
        Player player = user.asPlayer();
        World world = player.getWorld();
        Location location = player.getLocation();

        switch (scope) {
            case "server" -> server.playSound(sound, Sound.Emitter.self());
            case "world" -> world.playSound(sound, Sound.Emitter.self());
            case "location" -> world.playSound(sound, location.getX(), location.getY(), location.getZ());
            default -> player.playSound(sound, Sound.Emitter.self());

        }
    }
}
