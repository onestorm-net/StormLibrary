package net.onestorm.library.command;

import net.onestorm.library.user.OnlineUser;

import java.util.List;
import java.util.Optional;

public interface CommandManager {

    void registerCommand(Command command);

    void unregisterCommand(Command command);

    Optional<Command> getCommand(String alias);

    List<Command> getCommands();

    void execute(OnlineUser user, String command);
}
