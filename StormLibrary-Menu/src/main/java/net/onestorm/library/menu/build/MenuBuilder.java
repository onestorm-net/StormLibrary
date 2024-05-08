package net.onestorm.library.menu.build;

import net.onestorm.library.configuration.Section;
import net.onestorm.library.menu.Menu;

public interface MenuBuilder {

    String getName();

    Menu build(Section configuration);

}
