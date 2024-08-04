package net.onestorm.library.action.implementation;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.onestorm.library.action.Action;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.storage.StorageMap;

import java.util.Optional;

public class MessageActionBuilder implements Builder<Action> {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final String MESSAGE_NAME = "message";

    @Override
    public String getName() {
        return MESSAGE_NAME;
    }

    @Override
    public Action build(StorageMap storage) {
        Optional<String> optionalMessage = storage.getString("message");

        if (optionalMessage.isEmpty()) {
            throw new IllegalArgumentException("Missing \"message\" key in configuration");
        }

        Component message = MINI_MESSAGE.deserialize(optionalMessage.get());

        return new MessageAction(message);
    }
}
