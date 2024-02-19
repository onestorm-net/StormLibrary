package net.onestorm.library.configuration.file;

import net.onestorm.library.configuration.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public abstract class FileConfiguration extends MemorySection implements Configuration {

    /**
     * Load a configuration from file.
     * @param file File to load from.
     * @throws IOException Thrown when the given file cannot be read.
     */
    public void load(File file) throws IOException {

        FileInputStream stream = new FileInputStream(file);

        load(new InputStreamReader(stream, StandardCharsets.UTF_8));
    }

    /**
     * Load a configuration from a Reader.
     * @param reader Reader to load from.
     * @throws IOException Thrown when the given file cannot be read.
     */
    public void load(Reader reader) throws IOException {
        BufferedReader input;
        if (reader instanceof BufferedReader bufferedReader) {
            input = bufferedReader;
        } else {
            input = new BufferedReader(reader);
        }

        StringBuilder builder = new StringBuilder();

        try {
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        } finally {
            input.close();
        }

        loadFromString(builder.toString());
    }

    /**
     * Saves a configuration to a File.
     * @param file File to save to.
     * @throws IOException Thrown when the given file cannot be written to.
     */
    public void save(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }

        File parent = file.getParentFile();

        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new IOException("Failed to create directories for file: " + file.getAbsolutePath());
        }

        String data = saveToString();

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(data);
        }
    }

    /**
     * Sets the implementation's data from a String.
     * @param data Data as String.
     */
    protected abstract void loadFromString(String data);

    /**
     * Gets the implementation's data to save as String
     * @return Data as String.
     */
    protected abstract String saveToString();
}
