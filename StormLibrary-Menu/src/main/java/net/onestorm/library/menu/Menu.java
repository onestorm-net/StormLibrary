package net.onestorm.library.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.user.OnlineUser;

public interface Menu {

    void open(OnlineUser user);

    Component getTitle();

    void setTitle(Component title);

    void update();

}
