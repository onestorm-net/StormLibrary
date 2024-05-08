package net.onestorm.library.paper.menu.build;

import net.onestorm.library.configuration.Section;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.MenuBuilder;
import net.onestorm.library.paper.menu.ChestMenu;

public class ChestMenuBuilder implements MenuBuilder {

    private static final String MENU_NAME = "chest-menu";
    private static final int DEFAULT_MENU_SIZE = 9;
    private static final String DEFAULT_MENU_TITLE = "Chest";

    @Override
    public String getName() {
        return MENU_NAME;
    }

    @Override
    public Menu build(Section configuration) {

        int size = configuration.getInteger("size").orElse(DEFAULT_MENU_SIZE);
        String title = configuration.getString("title").orElse(DEFAULT_MENU_TITLE);

        return new ChestMenu(size, title);
    }
}
