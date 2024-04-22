package net.onestorm.library.menu.element;

import net.onestorm.library.menu.Menu;

import java.util.List;
import java.util.Optional;

public interface ElementMenu<I> extends Menu {

    List<Element<I>> getElements();

    List<Element<I>> getElements(String name);

    Optional<Element<I>> getElement(String id);

    void addElement(Element<I> element);

    void removeElement(Element<I> element);

    void removeElement(String id);

}
