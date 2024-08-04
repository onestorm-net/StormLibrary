package net.onestorm.library.menu.build;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

import java.util.Optional;

public abstract class AbstractMenuBuilder implements MenuBuilder {

    @Override
    public Menu build(StorageMap storage, Factory<Element> elementFactory) {
        Menu menu = createMenu(storage);

        Optional<StorageMap> optionalElementMap = storage.getMap("elements");

        if (optionalElementMap.isEmpty()) {
            return menu;
        }

        StorageMap elementMap = optionalElementMap.get();

        elementMap.forEach((key, value) -> {
            if (!(value instanceof StorageMap storageMap)) {
                return;
            }

            Element element;
            try {
                element = elementFactory.build(storageMap);
            } catch (BuildException e) {
                return;
            }

            menu.addElement(element);

        });

        return menu;
    }

    protected abstract Menu createMenu(StorageMap configuration);
}
