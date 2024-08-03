package net.onestorm.library.menu.element.build;

import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

import java.util.List;

public interface ElementFactory {

    void registerBuilder(ElementBuilder builder);

    void unregisterBuilder(ElementBuilder builder);

    List<Element> createElements(StorageMap storage);

}
