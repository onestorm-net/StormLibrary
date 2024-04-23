package net.onestorm.library.menu.element;


import net.onestorm.library.user.OnlineUser;

import java.util.Map;

public interface Element<I> {

    String getName();

    Map<Integer, I> getItems(OnlineUser user);

    // Iterable<Integer> getSlots(); // not needed anymore?

}
