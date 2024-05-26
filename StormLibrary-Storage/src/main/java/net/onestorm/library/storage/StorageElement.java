package net.onestorm.library.storage;

public interface StorageElement {

    /**
     * Gets the root Storage of this Storage Element
     * @return the root Storage
     */
    Storage getRoot();

    /**
     * Gets the parent of this Storage Element
     * @return
     */
    StorageElement getParent();

    /**
     * Gets this Element as a StorageList (if possible)
     * @throws ClassCastException when Element could not be casted to a StorageList
     * @return the StorageList
     */
    default StorageList asList() {
        return (StorageList) this;
    }

    /**
     * Gets this Element as a StorageMap (if possible)
     * @throws ClassCastException when Element could not be casted to a StorageMap
     * @return the StorageMap
     */
    default StorageMap asMap() {
        return (StorageMap) this;
    }

}
