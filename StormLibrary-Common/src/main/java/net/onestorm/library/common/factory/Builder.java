package net.onestorm.library.common.factory;

import net.onestorm.library.storage.StorageMap;

public interface Builder<T> {

    String getName();

    T build(StorageMap storage);

}
