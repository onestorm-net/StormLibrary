package net.onestorm.library.requirement.implementation;

import net.onestorm.library.requirement.AbstractRequirement;
import net.onestorm.library.user.User;

public class PermissionRequirement extends AbstractRequirement {

    private final String node;

    public PermissionRequirement(boolean isInverted, String node) {
        super(isInverted);
        this.node = node;
    }

    @Override
    protected boolean onCheck(User user) {
        return user.hasPermission(node);
    }
}
