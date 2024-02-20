package net.onestorm.library.action.implementation;

import net.kyori.adventure.text.Component;
import net.onestorm.library.action.Action;
import net.onestorm.library.user.OnlineUser;


public class MessageAction implements Action {

    private final Component message;

    public MessageAction(Component message) {
        this.message = message;
    }

    @Override
    public void execute(OnlineUser user) {
        user.sendMessage(message);
    }
}
