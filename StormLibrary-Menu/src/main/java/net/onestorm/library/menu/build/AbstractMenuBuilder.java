package net.onestorm.library.menu.build;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.build.ElementFactory;
import net.onestorm.library.storage.StorageMap;

import java.util.List;
import java.util.Optional;

public abstract class AbstractMenuBuilder implements MenuBuilder {

    @Override
    public Menu build(ElementFactory elementManager, StorageMap configuration) {
        Menu menu = createMenu(configuration);

        Optional<StorageMap> optionalElementMap = configuration.getMap("elements");

        if (optionalElementMap.isEmpty()) {
            return menu;
        }

        List<Element> elementList = elementManager.createElements(optionalElementMap.get());
        menu.addElements(elementList);

        return menu;
    }

    protected abstract Menu createMenu(StorageMap configuration);
}
