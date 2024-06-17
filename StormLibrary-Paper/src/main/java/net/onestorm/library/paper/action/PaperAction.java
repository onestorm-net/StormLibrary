package net.onestorm.library.paper.action;

import net.onestorm.library.action.Action;
import net.onestorm.library.paper.user.PaperUser;
import net.onestorm.library.user.User;

public abstract class PaperAction implements Action {
    @Override
    public void execute(User user) {
        if (!(user instanceof PaperUser paperUser)) {
            throw new IllegalArgumentException("This action needs a PaperUser to execute");
        }
        execute(paperUser);
    }

    abstract void execute(PaperUser user);
}
