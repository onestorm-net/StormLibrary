package net.onestorm.library.common.context;

import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.storage.StorageMap;

import java.util.Locale;
import java.util.Optional;

public class StorageBuildContext implements BuildContext {

    private final StorageMap storage;

    public StorageBuildContext(StorageMap storage) {
        this.storage = storage;
    }

    @Override
    public String getName() {
        Optional<String> optionalName = storage.getString("name");

        if (optionalName.isEmpty()) {
            throw new BuildException("Missing \"name\" key in storage while building.");
        }

        return optionalName.get().toLowerCase(Locale.ENGLISH);
    }

    /**
     * Retrieves the {@code StorageMap} used to create the "product."
     *
     * @return the storage map
     */
    public StorageMap getStorage() {
        return storage;
    }
}
