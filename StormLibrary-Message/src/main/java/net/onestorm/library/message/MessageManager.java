package net.onestorm.library.message;

public interface MessageManager {

    /**
     * Retrieves a {@link MessageBuilder} for the first available message key from the provided list of keys.
     * If multiple keys are supplied, it will return the {@link MessageBuilder} associated with the first key
     * that has a corresponding message. If none of the keys are found, it returns an empty {@link MessageBuilder}
     * (i.e., a {@link MessageBuilder} with no message set).
     *
     * @param keys one or more keys identifying the message templates or entries to search for
     * @return a {@link MessageBuilder} instance for the first key that exists,
     * or an empty {@link MessageBuilder} if no corresponding messages are found for any of the provided keys
     * @throws IllegalArgumentException if no keys are provided
     */
    MessageBuilder getMessage(String... keys);

}
