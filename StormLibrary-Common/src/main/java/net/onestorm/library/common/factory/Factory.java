package net.onestorm.library.common.factory;

import net.onestorm.library.storage.StorageMap;

public interface Factory<P> {

    /**
     * Builds a "product" with the given configurations. The name from the builder should be gained
     * from {@code StorageMap#getString("name")}
     * @param storage The StorageMap configuration
     * @return The "product"
     */
    P build(StorageMap storage);

    /**
     * Register a builder for this Factory
     * @param builder the builder
     */
    void registerBuilder(Builder<P> builder);

    /**
     * Unregister a builder for this Factory
     * @param builder the builder
     */
    void unregisterBuilder(Builder<P> builder);

}
