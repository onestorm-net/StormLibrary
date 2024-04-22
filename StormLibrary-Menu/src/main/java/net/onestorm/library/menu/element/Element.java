package net.onestorm.library.menu.element;


public interface Element<I> {

    String getName();

    I getItem(int index);

    Iterable<Integer> getSlots();

}
