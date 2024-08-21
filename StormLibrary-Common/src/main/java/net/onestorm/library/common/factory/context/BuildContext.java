package net.onestorm.library.common.factory.context;

import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.storage.StorageMap;

public interface BuildContext {

    /**
     * Gets the name of the used builder. Used for {@link Factory} implementations
     * @return the name of the builder
     */
    String getName();

    static BuildContext fromStorage(StorageMap storage) {
        return new StorageBuildContext(storage);
    }

}
