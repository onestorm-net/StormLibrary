package net.onestorm.library.menu.element;


import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.slot.Slot;
import net.onestorm.library.user.OnlineUser;

import java.util.Map;

public interface Element {

    String getName();

    Map<Integer, Slot> getSlots(Menu menu);

}
