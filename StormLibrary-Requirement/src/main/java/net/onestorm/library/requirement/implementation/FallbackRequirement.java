package net.onestorm.library.requirement.implementation;

import net.onestorm.library.requirement.Requirement;
import net.onestorm.library.user.User;

/**
 * Fallback requirement, used when a configuration error was made.
 */
public class FallbackRequirement implements Requirement {

    private static final boolean FALLBACK_STATE = false;

    @Override
    public boolean check(User user) {
        return FALLBACK_STATE;
    }
}
