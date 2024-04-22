package net.onestorm.library.menu.element;

import net.onestorm.library.menu.Menu;

import java.util.List;
import java.util.Optional;

public interface ElementMenu extends Menu {

    List<Element> getElements();

    List<Element> getElements(String name);

    Optional<Element> getElement(String id);

    void addElement(Element element);

    void removeElement(Element element);

    void removeElement(String id);

}
