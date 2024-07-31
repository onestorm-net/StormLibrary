package net.onestorm.library.storage;

public interface Storage extends StorageMap {

    /**
     * Gets the path separator used in this storage
     * @return the path separator
     */
    char getPathSeparator();

    /**
     * Sets the path separator used in this storage
     * @param separator the path separator
     */
    void setPathSeparator(char separator);

}
