package net.onestorm.library.storage.file;

import net.onestorm.library.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public interface FileStorage extends Storage {

    /**
     * Load storage data from file.
     * @param file File to load from.
     * @throws IOException Thrown when the given file cannot be read.
     */
    void load(File file) throws IOException;

    /**
     * Load storage data from a Reader.
     * @param reader Reader to load from.
     * @throws IOException Thrown when the given file cannot be read.
     */
    void load(Reader reader) throws IOException;

    /**
     * Saves storage data to a File.
     * @param file File to save to.
     * @throws IOException Thrown when the given file cannot be written to.
     */
    void save(File file) throws IOException;

}
