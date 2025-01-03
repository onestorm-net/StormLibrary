package net.onestorm.library.paper.action;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.onestorm.library.action.Action;
import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.context.BuildContext;
import net.onestorm.library.common.factory.context.StorageBuildContext;
import net.onestorm.library.storage.StorageMap;
import org.bukkit.Server;

import java.util.Locale;
import java.util.Optional;

public class PlaySoundActionBuilder implements Builder<Action> {

    private static final String BUILDER_NAME = "play-sound";

    private final Server server;

    public PlaySoundActionBuilder(Server server) {
        this.server = server;
    }

    @Override
    public String getName() {
        return BUILDER_NAME;
    }

    @Override
    public Action build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("Context is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();
        Optional<String> optionalSound = storage.getString("sound");

        if (optionalSound.isEmpty()) {
            throw new IllegalArgumentException("Missing \"sound\" key in configuration");
        }

        String scope = storage.getString("scope").orElse("player");
        String namespace = storage.getString("namespace").orElse("minecraft");
        String sourceName = storage.getString("source").orElse("PLAYER");
        float volume = storage.getFloat("volume").orElse(1F);
        float pitch = storage.getFloat("pitch").orElse(1F);

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
