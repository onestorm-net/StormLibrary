package net.onestorm.library.action;

import net.onestorm.library.common.context.UserContext;

public interface Action {

    /**
     * Execute the action
     * @param context which the action is used on
     */
    void execute(UserContext context);

}
