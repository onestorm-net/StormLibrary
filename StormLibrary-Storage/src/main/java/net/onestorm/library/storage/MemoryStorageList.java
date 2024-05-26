package net.onestorm.library.storage;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class MemoryStorageList implements StorageList {

    private final List<Object> list = new ArrayList<>();
    private final Storage root;
    private final StorageElement parent;

    public MemoryStorageList() {
        if (!(this instanceof Storage)) {
            throw new IllegalStateException("Not a root storage object");
        }

        this.root = (Storage) this;
        this.parent = null;
    }

    public MemoryStorageList(StorageElement parent) {
        this.root = parent.getRoot();
        this.parent = parent;
    }

    @Override
    public Storage getRoot() {
        return root;
    }

    @Override
    public StorageElement getParent() {
        return parent;
    }

    @Override
    public void add(Object value) {
        list.add(value);
    }

    @Override
    public StorageMap addMap() {
        StorageMap result = new MemoryStorageMap(this);
        list.add(result);
        return result;
    }

    @Override
    public StorageList addList() {
        StorageList result = new MemoryStorageList(this);
        list.add(result);
        return result;
    }

    @Override
    public boolean remove(Object value) {
        return list.remove(value);
    }

    @Override
    public boolean removeIf(Predicate<Object> filter) {
        return list.removeIf(filter);
    }

    @NotNull
    @Override
    public Iterator<Object> iterator() {
        return list.iterator();
    }

}
