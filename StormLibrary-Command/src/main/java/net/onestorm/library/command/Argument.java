package net.onestorm.library.command;

public interface Argument {

    Argument addArgument(Argument argument);

    Argument suggest();

    Argument execute();

}
