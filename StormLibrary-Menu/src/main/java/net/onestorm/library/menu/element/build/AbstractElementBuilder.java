package net.onestorm.library.menu.element.build;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.context.BuildContext;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.IdentifiableElement;
import net.onestorm.library.menu.element.PrioritizableElement;
import net.onestorm.library.menu.element.build.context.ElementBuildContext;
import net.onestorm.library.storage.StorageMap;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElementBuilder implements Builder<Element> {

    protected static final int DEFAULT_WIDTH = 1;
    protected static final int DEFAULT_HEIGHT = 1;

    @Override
    public Element build(BuildContext context) {
        if (!(context instanceof ElementBuildContext elementContext)) {
            throw new BuildException("BuildContext is not an instance of StorageBuildContext.");
        }

        StorageMap storage = elementContext.getStorage();
        Menu menu = elementContext.getMenu();

        String name = getName();

        int x = storage.getInteger("x").orElseThrow(() -> new BuildException("Missing \"x\" key in storage while building: " + name));
        int y = storage.getInteger("y").orElseThrow(() -> new BuildException("Missing \"y\" key in storage while building: " + name));
        int width = storage.getInteger("width").orElse(DEFAULT_WIDTH);
        int height = storage.getInteger("height").orElse(DEFAULT_HEIGHT);

        List<Integer> slots = new ArrayList<>();
        for (int indexY = y; indexY < y + height; indexY++) {
            for (int indexX = x; indexX < x + width; indexX++) {
                slots.add(indexY * menu.getWidth() + indexX);
            }
        }

        Element element = createElement(storage, menu, slots);

        if (element instanceof IdentifiableElement identifiable) {
            storage.getString("identifier").ifPresent(identifiable::setIdentifier);
        }

        if (element instanceof PrioritizableElement prioritizable) {
            storage.getInteger("priority").ifPresent(prioritizable::setPriority);
        }

        return element;
    }

    protected abstract Element createElement(StorageMap storage, Menu menu, List<Integer> slots);
}
