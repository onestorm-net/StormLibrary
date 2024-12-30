package net.onestorm.library.paper.action;

import net.onestorm.library.paper.user.PaperUser;

public class PlayerCommandAction extends PaperAction {

    private final String command;

    public PlayerCommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(PaperUser user) {
        user.asPlayer().chat("/" + command);
    }
}
