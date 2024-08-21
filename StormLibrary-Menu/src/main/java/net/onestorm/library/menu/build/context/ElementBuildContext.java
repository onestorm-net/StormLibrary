package net.onestorm.library.menu.build.context;

import net.onestorm.library.common.factory.context.StorageBuildContext;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.storage.StorageMap;

public class ElementBuildContext extends StorageBuildContext {

    private final Menu menu;

    public ElementBuildContext(StorageMap storage, Menu menu) {
        super(storage);
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }
}
