package net.onestorm.library.menu.element;

import net.onestorm.library.menu.slot.Slot;


public interface Element {

    String getName();

    Slot getSlot(int index);

    Iterable<Integer> getSlotIndexes();

}
