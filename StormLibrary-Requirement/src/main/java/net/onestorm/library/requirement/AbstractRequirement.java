package net.onestorm.library.requirement;

import net.onestorm.library.common.context.UserContext;

public abstract class AbstractRequirement implements Requirement {

    private final boolean isInverted;

    public AbstractRequirement(boolean isInverted) {
        this.isInverted = isInverted;
    }

    @Override
    public boolean check(UserContext context) {
        if (isInverted) {
            return !onCheck(context);
        }
        return onCheck(context);
    }

    /**
     * Checks if the context meets the requirement.
     * @param context The context.
     * @return True when context meets the requirement.
     */
    protected abstract boolean onCheck(UserContext context);
}
