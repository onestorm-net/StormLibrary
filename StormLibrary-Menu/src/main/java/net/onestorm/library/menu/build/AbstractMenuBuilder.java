package net.onestorm.library.menu.build;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.common.factory.context.BuildContext;
import net.onestorm.library.common.factory.context.StorageBuildContext;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.build.context.ElementBuildContext;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

import java.util.Optional;

public abstract class AbstractMenuBuilder implements Builder<Menu> {

    protected final Factory<Element> elementFactory;

    protected AbstractMenuBuilder(Factory<Element> elementFactory) {
        this.elementFactory = elementFactory;
    }

    @Override
    public Menu build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("Context is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();
        Menu menu = createMenu(storage);

        storage.getString("title").ifPresent(menu::setTitle);

        Optional<StorageMap> optionalElementMap = storage.getMap("elements");

        if (optionalElementMap.isEmpty()) {
            return menu;
        }

        StorageMap elementMap = optionalElementMap.get();

        elementMap.forEach((key, value) -> {
            if (!(value instanceof StorageMap elementStorage)) {
                return;
            }

            Element element;
            try {
                element = elementFactory.build(new ElementBuildContext(elementStorage, menu));
            } catch (BuildException e) {
                return;
            }

            menu.addElement(element);

        });

        return menu;
    }

    protected abstract Menu createMenu(StorageMap configuration);
}
