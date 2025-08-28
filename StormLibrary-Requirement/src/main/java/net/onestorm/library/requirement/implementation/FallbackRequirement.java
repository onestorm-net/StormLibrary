package net.onestorm.library.requirement.implementation;

import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.requirement.Requirement;

/**
 * Fallback requirement, used when a configuration error was made.
 */
public class FallbackRequirement implements Requirement {

    private static final boolean FALLBACK_STATE = false;

    @Override
    public boolean check(UserContext context) {
        return FALLBACK_STATE;
    }
}
