package net.onestorm.library.menu;

import net.kyori.adventure.text.Component;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.user.OnlineUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Menu {

    void open(OnlineUser user);

    void open(OnlineUser user, Map<String, Object> options);

    Map<String, Object> getOptions();

    Component getTitle();

    void setTitle(Component title);

    OnlineUser getOwner();

    void update();

    List<Element> getElements();

    List<Element> getElements(String name);

    Optional<Element> getElement(String id);

    void addElement(Element element);

    void removeElement(Element element);

    void removeElement(String id);

}
