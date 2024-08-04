package net.onestorm.library.common.factory;

import net.onestorm.library.storage.StorageMap;

public interface Builder<P> {

    /**
     * Gets the name of this builder. Used for {@link Factory} implementations
     * @return the name of the builder
     */
    String getName();

    /**
     * Builds a "product" with the given configurations
     * @param storage The StorageMap configuration
     * @return The "product"
     */
    P build(StorageMap storage);

}
