package net.onestorm.library.menu.element.build.context;

import net.onestorm.library.common.context.StorageBuildContext;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.storage.StorageMap;

public class ElementBuildContext extends StorageBuildContext {

    private final Menu menu;

    public ElementBuildContext(StorageMap storage, Menu menu) {
        super(storage);
        this.menu = menu;
    }

    /**
     * Retrieves the menu for which this element is being built.
     *
     * @return the menu associated with this build context
     */
    public Menu getMenu() {
        return menu;
    }
}
