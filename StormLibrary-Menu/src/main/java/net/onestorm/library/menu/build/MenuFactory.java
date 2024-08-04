package net.onestorm.library.menu.build;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.storage.StorageMap;

public interface MenuFactory {

    Menu createMenu(StorageMap configuration);

    void registerBuilder(MenuBuilder builder);

    void unregisterBuilder(MenuBuilder builder);

}
