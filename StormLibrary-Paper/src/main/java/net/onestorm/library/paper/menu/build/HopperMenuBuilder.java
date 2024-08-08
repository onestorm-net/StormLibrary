package net.onestorm.library.paper.menu.build;

import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.AbstractMenuBuilder;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.paper.menu.HopperMenu;
import net.onestorm.library.storage.StorageMap;

public class HopperMenuBuilder extends AbstractMenuBuilder {

    private static final String MENU_NAME = "hopper-menu";
    private static final String DEFAULT_MENU_TITLE = "Hopper";

    public HopperMenuBuilder(Factory<Element> elementFactory) {
        super(elementFactory);
    }

    @Override
    public String getName() {
        return MENU_NAME;
    }

    @Override
    protected Menu createMenu(StorageMap configuration) {
        String title = configuration.getString("title").orElse(DEFAULT_MENU_TITLE);

        return new HopperMenu(title);
    }
}
