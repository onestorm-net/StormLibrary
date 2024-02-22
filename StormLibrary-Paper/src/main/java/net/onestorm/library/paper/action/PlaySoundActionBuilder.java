package net.onestorm.library.paper.action;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.sound.Sound;
import net.onestorm.library.action.Action;
import net.onestorm.library.action.ActionBuilder;
import net.onestorm.library.configuration.Section;
import org.bukkit.Server;

import java.util.Locale;
import java.util.Optional;

public class PlaySoundActionBuilder implements ActionBuilder {

    private static final String ACTION_BUILDER_NAME = "play-sound";

    private final Server server;

    public PlaySoundActionBuilder(Server server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return ACTION_BUILDER_NAME;
    }

    @Override
    public Action build(Section configuration) {

        Optional<String> optionalSound = configuration.getString("sound");

        if (optionalSound.isEmpty()) {
            throw new IllegalArgumentException("Missing \"sound\" key in configuration");
        }

        String scope = configuration.getString("scope").orElse("player");
        String namespace = configuration.getString("namespace").orElse("minecraft");
        String sourceName = configuration.getString("source").orElse("PLAYER");
        float volume = configuration.getFloat("volume").orElse(1F);
        float pitch = configuration.getFloat("pitch").orElse(1F);

        Sound.Source source;
        try {
            source = Sound.Source.valueOf(sourceName.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException e) {
            source = Sound.Source.PLAYER;
        }

        Sound sound = Sound.sound(Key.key(namespace, optionalSound.get()), source, volume, pitch);

        return new PlaySoundAction(server, scope, sound);
    }
}
