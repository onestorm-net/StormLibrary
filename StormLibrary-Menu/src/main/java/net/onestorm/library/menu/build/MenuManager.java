package net.onestorm.library.menu.build;

import net.onestorm.library.configuration.Section;
import net.onestorm.library.menu.Menu;

public interface MenuManager {

    Menu createMenu(Section section);

    void registerBuilder(MenuBuilder builder);

    void unregisterBuilder(MenuBuilder builder);

}
