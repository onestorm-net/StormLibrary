package test;

import net.onestorm.library.command.Argument;
import net.onestorm.library.command.Context;

public class StringArgument implements Argument {
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
