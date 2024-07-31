package net.onestorm.library.user;

import net.kyori.adventure.text.Component;

public interface User extends OfflineUser {

    /**
     * Sends a message to the user.
     * @param message message as a adventure component.
     */
    void sendMessage(Component message);

    /**
     * Checks if the user has permission for the provided node.
     * @param node The permission node.
     * @return True when user has permission.
     */
    boolean hasPermission(String node);

}
