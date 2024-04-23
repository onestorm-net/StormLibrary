package net.onestorm.library.menu;

public interface MenuClickEvent<I> {

    I getCursorItem();

    //void setCursorItem(I item); // deprecated in bukkit/spigot/paper api, probably not something we should support

    I getClickedItem();

    void setClickedItem(I item);

    int getSlot();

    Menu getMenu();

    //boolean isCreativeClick(); // add it later

    //int getMode(); // add it later

    //int getButton(); // add it later

}
