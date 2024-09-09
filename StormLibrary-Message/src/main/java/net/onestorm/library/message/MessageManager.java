package net.onestorm.library.message;

public interface MessageManager {

    /**
     * Retrieves a {@link MessageBuilder} for the specified message key.
     *
     * @param key the key identifying the message template or entry
     * @return a {@link MessageBuilder} instance for building and formatting the message
     */
    MessageBuilder getMessage(String key);

}
