package net.onestorm.library.user;

import java.util.UUID;

public interface OfflineUser {

    /**
     * Gets the UUID of this user.
     * @return UUID of user.
     */
    UUID getUuid();

    /**
     * Gets the (last known) username of this user.
     * @return Username of user.
     */
    String getUsername();

}
