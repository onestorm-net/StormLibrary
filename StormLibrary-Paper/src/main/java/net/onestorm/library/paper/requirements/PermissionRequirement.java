package net.onestorm.library.paper.requirements;

import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.paper.context.PlayerContext;
import net.onestorm.library.requirement.AbstractRequirement;

public class PermissionRequirement extends AbstractRequirement {

    private final String node;

    public PermissionRequirement(boolean isInverted, String node) {
        super(isInverted);
        this.node = node;
    }

    @Override
    protected boolean onCheck(UserContext context) {
        if (!(context instanceof PlayerContext playerContext)) {
            return false;
        }

        return playerContext.getPlayer().hasPermission(node);
    }
}
