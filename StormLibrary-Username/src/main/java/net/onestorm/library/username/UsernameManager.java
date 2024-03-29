package net.onestorm.library.username;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;


public interface UsernameManager {

    /**
     * Gets the requested username by uuid.
     * <p>
     * When the username is not found this method should return an empty Optional.
     * @param uuid UUID of a user like object, which has a username.
     * @return The username.
     */
    CompletableFuture<Optional<String>> getUsername(UUID uuid);

    /**
     * Gets the requested uuid by username.
     * <p>
     * When the uuid is not found this method should return an empty Optional.
     * @param username Username of a user like object, which has a uuid.
     * @return The uuid.
     */
    CompletableFuture<Optional<UUID>> getUuid(String username);

}
