package net.onestorm.library.menu.element;

import net.onestorm.library.menu.slot.Slot;

import java.util.Optional;


public interface Element {

    String getName();

    Slot<?> getSlot(int index);

    Iterable<Integer> getSlotIndexes();

}
