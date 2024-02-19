package net.onestorm.library.requirement;

import net.onestorm.library.user.User;

public abstract class AbstractRequirement implements Requirement {

    private final boolean isInverted;

    public AbstractRequirement(boolean isInverted) {
        this.isInverted = isInverted;
    }

    @Override
    public boolean check(User user) {
        if (isInverted) {
            return !onCheck(user);
        }
        return onCheck(user);
    }

    /**
     * Checks if the user meets the requirement.
     * @param user The user.
     * @return True when user meets the requirement.
     */
    protected abstract boolean onCheck(User user);
}
