package net.onestorm.library.action;

import net.onestorm.library.configuration.Section;

import java.util.List;

public interface ActionManager {

    void registerBuilder(ActionBuilder builder);

    void unregisterBuilder(ActionBuilder builder);

    List<Action> getActions(Section configuration);


}
