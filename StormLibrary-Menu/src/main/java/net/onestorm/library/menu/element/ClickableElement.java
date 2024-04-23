package net.onestorm.library.menu.element;

import net.onestorm.library.menu.MenuClickEvent;

public interface ClickableElement<I> extends Element<I> {

    void onElementClick(MenuClickEvent<I> event);

}
