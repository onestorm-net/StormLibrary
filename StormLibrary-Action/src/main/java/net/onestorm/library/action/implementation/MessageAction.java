package net.onestorm.library.action.implementation;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.onestorm.library.action.Action;
import net.onestorm.library.action.ReplacePatternAction;
import net.onestorm.library.user.User;

import java.util.regex.Pattern;

public class MessageAction implements Action, ReplacePatternAction {

    private Component message;

    public MessageAction(Component message) {
        this.message = message;
    }

    @Override
    public void replace(Pattern pattern, String value) {
        TextReplacementConfig configuration = TextReplacementConfig.builder()
                .match(pattern)
                .replacement(value)
                .build();

        message = message.replaceText(configuration);
    }

    @Override
    public void execute(User user) {
        user.sendMessage(message);
    }
}
