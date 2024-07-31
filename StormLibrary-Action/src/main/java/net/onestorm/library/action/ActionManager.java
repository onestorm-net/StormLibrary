package net.onestorm.library.action;

import net.onestorm.library.storage.StorageMap;

import java.util.List;

public interface ActionManager {

    void registerBuilder(ActionBuilder builder);

    void unregisterBuilder(ActionBuilder builder);

    List<Action> getActions(StorageMap storage);


}
