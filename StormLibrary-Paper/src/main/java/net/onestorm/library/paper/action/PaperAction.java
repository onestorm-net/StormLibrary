package net.onestorm.library.paper.action;

import net.onestorm.library.action.Action;
import net.onestorm.library.paper.user.PaperOnlineUser;
import net.onestorm.library.user.OnlineUser;

public abstract class PaperAction implements Action {
    @Override
    public void execute(OnlineUser user) {
        if (!(user instanceof PaperOnlineUser paperUser)) {
            throw new IllegalArgumentException("This action needs a PaperUser to execute");
        }
        execute(paperUser);
    }

    abstract void execute(PaperOnlineUser user);
}
