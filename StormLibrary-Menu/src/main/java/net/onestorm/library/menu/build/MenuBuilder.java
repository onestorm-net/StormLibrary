package net.onestorm.library.menu.build;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.build.ElementFactory;
import net.onestorm.library.storage.StorageMap;

public interface MenuBuilder {

    String getName();

    Menu build(ElementFactory elementFactory, StorageMap configuration);

}
