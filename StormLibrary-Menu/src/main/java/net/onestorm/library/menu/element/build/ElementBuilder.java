package net.onestorm.library.menu.element.build;

import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

public interface ElementBuilder {

    String getName();

    Element build(StorageMap configuration);

}
