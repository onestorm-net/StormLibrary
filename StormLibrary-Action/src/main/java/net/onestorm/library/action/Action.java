package net.onestorm.library.action;

import net.onestorm.library.user.User;

public interface Action {

    /**
     * Execute the action
     * @param user User which the action is used on
     */
    void execute(User user);

}
