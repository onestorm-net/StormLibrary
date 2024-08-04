package net.onestorm.library.menu.build;

import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

public interface MenuBuilder extends Builder<Menu> {

    Menu build(StorageMap storage, Factory<Element> elementFactory);

    @Override
    default Menu build(StorageMap storage) {
        return build(storage, null);
    }
}
