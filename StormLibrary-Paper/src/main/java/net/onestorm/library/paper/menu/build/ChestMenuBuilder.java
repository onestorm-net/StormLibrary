package net.onestorm.library.paper.menu.build;

import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.AbstractMenuBuilder;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.paper.menu.ChestMenu;
import net.onestorm.library.storage.StorageMap;

public class ChestMenuBuilder extends AbstractMenuBuilder {

    private static final String MENU_NAME = "chest-menu";
    private static final int DEFAULT_MENU_SIZE = 9;

    public ChestMenuBuilder(Factory<Element> elementFactory) {
        super(elementFactory);
    }

    @Override
    public String getName() {
        return MENU_NAME;
    }

    @Override
    protected Menu createMenu(StorageMap configuration) {
        int size = configuration.getInteger("size").orElse(DEFAULT_MENU_SIZE);

        return new ChestMenu(size);
    }
}
