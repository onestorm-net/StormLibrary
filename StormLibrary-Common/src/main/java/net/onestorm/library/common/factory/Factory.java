package net.onestorm.library.common.factory;

import net.onestorm.library.storage.StorageMap;

public interface Factory<T> {

    T build(StorageMap storage);

    void registerBuilder(Builder<T> builder);

    void unregisterBuilder(Builder<T> builder);

}
