package net.onestorm.library.requirement;

import net.onestorm.library.common.context.UserContext;

public interface Requirement {

    /**
     * Checks if the context meets the requirement.
     * @param context The context.
     * @return True when context meets the requirement.
     */
    boolean check(UserContext context);

}
