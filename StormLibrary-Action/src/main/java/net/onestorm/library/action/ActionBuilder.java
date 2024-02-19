package net.onestorm.library.action;

import net.onestorm.library.configuration.Section;

public interface ActionBuilder {

    String getName();

    Action build(Section configuration);

}
