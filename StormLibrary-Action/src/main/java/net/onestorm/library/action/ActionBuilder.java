package net.onestorm.library.action;

import net.onestorm.library.storage.StorageMap;

public interface ActionBuilder {

    String getName();

    Action build(StorageMap storage);

}
