package net.onestorm.library.menu;

public interface ClickableMenu<I> extends Menu {

    void onMenuClick(MenuClickEvent<I> event);

}
