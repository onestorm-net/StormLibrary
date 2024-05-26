package net.onestorm.library.storage;

public class MemoryStorage extends MemoryStorageMap implements Storage {

    private char separator = '.';

    @Override
    public char getPathSeparator() {
        return separator;
    }

    @Override
    public void setPathSeparator(char separator) {
        this.separator = separator;
    }
}
