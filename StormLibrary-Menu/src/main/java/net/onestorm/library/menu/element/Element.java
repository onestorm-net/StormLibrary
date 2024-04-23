package net.onestorm.library.menu.element;


import net.onestorm.library.user.OnlineUser;

public interface Element<I> {

    String getName();

    I getItem(int index, OnlineUser user);

    Iterable<Integer> getSlots();

}
