package net.onestorm.library.paper.action;

import net.onestorm.library.action.Action;
import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.paper.context.PlayerContext;

public abstract class PaperAction implements Action {
    @Override
    public void execute(UserContext context) {
        if (!(context instanceof PlayerContext playerContext)) {
            throw new IllegalArgumentException("This action needs a PlayerContext (Paper) to execute");
        }
        execute(playerContext);
    }

    public abstract void execute(PlayerContext context);
}
