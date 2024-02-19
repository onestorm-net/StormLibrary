package net.onestorm.library.action;

import net.onestorm.library.configuration.Section;

import java.util.List;

public interface ActionManager {

    void registerActionBuilder(ActionBuilder builder);

    void unregisterActionBuilder(ActionBuilder builder);

    List<Action> getActions(Section configuration);


}
