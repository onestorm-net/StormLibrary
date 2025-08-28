package net.onestorm.library.paper.action;

import net.onestorm.library.paper.context.PlayerContext;

public class PlayerCommandAction extends PaperAction {

    private final String command;

    public PlayerCommandAction(String command) {
        this.command = command;
    }

    @Override
    public void execute(PlayerContext context) {
        context.getPlayer().chat("/" + command);
    }
}
