package net.onestorm.library.command;

import java.util.List;

public interface Command extends Argument {

    List<String> getAliases();

}
