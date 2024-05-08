package net.onestorm.library.menu;

import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.user.OnlineUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Menu {

    void open(OnlineUser user);

    void open(OnlineUser user, Map<String, Object> options);

    Map<String, Object> getOptions();

    String getTitle();

    void setTitle(String title);

    OnlineUser getOwner();

    int getWidth();

    int getHeight();

    int getSize();

    void updateCell(Cell cell);

    void update();

    List<Element> getElements();

    List<Element> getElementsByName(String name);

    Optional<Element> getElementById(String identifier);

    void addElement(Element element);

    void removeElement(Element element);

    void removeElementById(String identifier);

    void close();

}
