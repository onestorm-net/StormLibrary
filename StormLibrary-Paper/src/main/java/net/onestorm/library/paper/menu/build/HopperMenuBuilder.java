package net.onestorm.library.paper.menu.build;

import net.onestorm.library.configuration.Section;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.MenuBuilder;
import net.onestorm.library.paper.menu.ChestMenu;
import net.onestorm.library.paper.menu.HopperMenu;

public class HopperMenuBuilder implements MenuBuilder {

    private static final String MENU_NAME = "hopper-menu";
    private static final String DEFAULT_MENU_TITLE = "Hopper";
    @Override
    public String getName() {
        return MENU_NAME;
    }

    @Override
    public Menu build(Section configuration) {

        String title = configuration.getString("title").orElse(DEFAULT_MENU_TITLE);

        return new HopperMenu(title);
    }
}
