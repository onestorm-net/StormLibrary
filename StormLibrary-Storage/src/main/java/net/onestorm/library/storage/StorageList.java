package net.onestorm.library.storage;

import java.util.function.Predicate;

public interface StorageList extends StorageElement, Iterable<Object> {

    /**
     * Adds a new (primitive) value to this StorageList
     * <br>
     * NOTE: use {@link StorageList#addMap()} and {@link StorageList#addList()} for maps and lists, otherwise getters and setters might stop working
     * @param value the value to be added.
     */
    void add(Object value);

    /**
     * Adds a new StorageMap to this StorageList
     * @return the newly added StorageMap
     */
    StorageMap addMap();

    /**
     * Adds a new StorageList to this StorageList
     * @return the newly added StorageMap
     */
    StorageList addList();

    /**
     * Removes the first specified object from this StorageList
     * @param value the object which needs to be removed
     * @return true if an object was successfully removed
     */
    boolean remove(Object value);

    /**
     * Removes all objects from this StorageList which meet the requirement from the filter
     * @param filter the filter
     * @return true if an object was successfully removed
     */
    boolean removeIf(Predicate<Object> filter);

}
