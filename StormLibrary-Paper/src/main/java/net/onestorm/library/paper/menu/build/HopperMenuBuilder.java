package net.onestorm.library.paper.menu.build;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.MenuBuilder;
import net.onestorm.library.paper.menu.HopperMenu;
import net.onestorm.library.storage.StorageMap;

public class HopperMenuBuilder implements MenuBuilder {

    private static final String MENU_NAME = "hopper-menu";
    private static final String DEFAULT_MENU_TITLE = "Hopper";
    @Override
    public String getName() {
        return MENU_NAME;
    }

    @Override
    public Menu build(StorageMap configuration) {

        String title = configuration.getString("title").orElse(DEFAULT_MENU_TITLE);

        return new HopperMenu(title);
    }
}
