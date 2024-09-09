package net.onestorm.library.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;

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
     *
     * @return the built {@link Component} message
     * @throws NullPointerException if required data is missing and the message cannot be built
     */
    Component build();

    /**
     * Attempts to build the {@link Component} message, returning an {@link Optional}.
     *
     * @return an {@link Optional} containing the built {@link Component} if successful, or {@link Optional#empty()} if not
     */
    default Optional<Component> tryBuild() {
        Component component = null;
        try {
            component = build();
        } catch (Exception e) {
            // ignored
        }
        return Optional.ofNullable(component);
    }

    /**
     * Builds the {@link Component} and passes it to the provided {@link Consumer}, or throws an exception if building fails.
     *
     * @param consumer the {@link Consumer} to accept the built {@link Component}
     * @throws IllegalStateException if the message cannot be built
     */
    default void buildThen(Consumer<Component> consumer) {
        Component component = build();
        consumer.accept(component);
    }
}
