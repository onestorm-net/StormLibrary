package net.onestorm.library.command;

import java.util.List;

public abstract class SimpleCommand implements Command {
    @Override
    public Argument addArgument(Argument argument) {
        return null;
    }

    @Override
    public Argument suggest(Context context) {
        return null;
    }

    @Override
    public Argument execute(Context context) {
        return null;
    }

}
