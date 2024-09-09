package net.onestorm.library.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.Tag;
import net.kyori.adventure.text.minimessage.tag.TagPattern;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class MessageBuilderImpl implements MessageBuilder {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    private String message;
    private String defaultMessage = "";
    private final Collection<TagResolver> tagResolvers = new ArrayList<>();

    public MessageBuilderImpl() {
    }

    public MessageBuilderImpl(String message) {
        this.message = message;
    }

    @Override
    public MessageBuilder withMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public MessageBuilder withDefault(String message) {
        this.defaultMessage = message;
        return this;
    }

    @Override
    public MessageBuilder withPlaceholder(@TagPattern String key, String value) {
        return withPlaceholder(key, Component.text(value));
    }

    @Override
    public MessageBuilder withPlaceholder(@TagPattern String key, Component value) {
        tagResolvers.add(Placeholder.component(key, value));
        return this;
    }

    @Override
    public Component build() {
        String finalMessage = message;
        if (message == null) {
            if (defaultMessage == null) {
                throw new IllegalArgumentException("message and defaultMessage are null");
            }
            finalMessage = defaultMessage;
        }

        return MINI_MESSAGE.deserialize(finalMessage, TagResolver.resolver(tagResolvers));
    }

    @Override
    public @NotNull Component asComponent() {
        return build();
    }
}
