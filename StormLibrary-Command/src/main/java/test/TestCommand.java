package test;

import net.onestorm.library.command.SimpleCommand;

import java.util.List;

public class TestCommand extends SimpleCommand {

    private final static String COMMAND_ALIAS = "test";
    private final static List<String> COMMAND_ALIASES = List.of(COMMAND_ALIAS, "t");

    public TestCommand() {
        addArgument(new UserArgument()
                .addArgument(new StringArgument().execute((context) -> {
                    // setname
                }))
                .execute((context) -> {
                    // usage: /test <user> setname <name>
                })
        );
    }

    @Override
    public List<String> getAliases() {
        return COMMAND_ALIASES;
    }
}
