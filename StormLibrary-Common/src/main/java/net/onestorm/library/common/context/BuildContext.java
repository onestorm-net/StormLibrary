package net.onestorm.library.common.context;

import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.storage.StorageMap;

public interface BuildContext {

    /**
     * Retrieves the name of the builder in use. Primarily intended for implementations of {@link Factory}.
     *
     * @return the name of the builder
     */
    String getName();

    /**
     * Creates a build context from the specified storage.
     *
     * @param storage the storage map used to create the build context
     * @return the newly created build context
     */
    static BuildContext fromStorage(StorageMap storage) {
        return new StorageBuildContext(storage);
    }

}
