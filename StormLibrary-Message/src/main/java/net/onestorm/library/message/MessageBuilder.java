package net.onestorm.library.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Optional;
import java.util.function.Consumer;

public interface MessageBuilder extends ComponentLike {

    /**
     * Sets the base message for this builder, which can contain placeholders.
     *
     * @param message the message template with placeholders (e.g., "Hello, <name>!")
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder withMessage(String message);

    /**
     * Sets a default message to be used if no message is explicitly provided.
     *
     * @param message the default message to use if no custom message is set
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder withDefault(String message);

    /**
     * Allows this message to be explicitly disabled via configuration.
     * <p>
     * When enabled, if the message value is set to {@code "none"} in the configuration,
     * {@link #tryBuild()} will return {@link Optional#empty()}, and calling {@link #build()}
     * will throw an exception. This is useful for optional or cosmetic messages
     * (e.g. menu headers, tooltips) that can be hidden entirely.
     *
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder allowDisabled();

    /**
     * Disallows this message from being explicitly disabled via configuration.
     * <p>
     * When disabled (the default), if the message value is set to {@code "none"} in the configuration,
     * the value will be ignored and the default message (if provided) will be used instead.
     * This ensures that important or required messages cannot be suppressed.
     *
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder disallowDisabled();

    /**
     * Adds a {@link TagResolver} to the {@link MessageBuilder} for resolving placeholders or tags within the message.
     *
     * @param resolver the {@link TagResolver} to be added to this {@link MessageBuilder}
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder withTagResolver(TagResolver resolver);

    /**
     * Replaces a placeholder within the message with the provided {@link Component}.
     *
     * @param key the placeholder key to replace (without angle brackets, e.g., "name")
     * @param value the {@link Component} to insert in place of the placeholder
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder withPlaceholder(String key, Component value);

    /**
     * Replaces a placeholder within the message with the provided string value.
     *
     * @param key the placeholder key to replace (without angle brackets, e.g., "name")
     * @param value the string value to insert in place of the placeholder
     * @return the current {@link MessageBuilder} instance for chaining
     */
    MessageBuilder withPlaceholder(String key, String value);

    /**
     * Builds and returns the {@link Component} message.
     * <p>
     * This method always attempts to produce a {@link Component}. If the message
     * is missing, the configured default will be used. If no default is available,
     * or the message is explicitly disabled, an exception will be thrown.
     *
     * @return the built {@link Component} message
     * @throws IllegalArgumentException if the message is missing, has no default,
     *                                  or is explicitly disabled
     */
    Component build();

    /**
     * Attempts to build the {@link Component} message.
     * <p>
     * If the message is present or a default is provided, a built {@link Component}
     * will be returned. If the message is missing with no default, or explicitly
     * disabled, an empty {@link Optional} will be returned instead.
     *
     * @return an {@link Optional} containing the built {@link Component},
     *         or {@link Optional#empty()} if missing/disabled
     */
    Optional<Component> tryBuild();

    /**
     * Attempts to build the {@link Component} message and pass it to the given consumer.
     * <p>
     * If building succeeds, the resulting {@link Component} will be passed to the
     * consumer. If the message is missing with no default, or explicitly disabled,
     * nothing will be passed.
     *
     * @param consumer the {@link Consumer} to accept the built {@link Component}
     */
    default void buildThen(Consumer<Component> consumer) {
        tryBuild().ifPresent(consumer);
    }
}
