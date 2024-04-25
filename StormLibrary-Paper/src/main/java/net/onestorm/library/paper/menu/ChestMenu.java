package net.onestorm.library.paper.menu;

import net.kyori.adventure.text.Component;

public class ChestMenu extends PaperMenu {

    private final int height;

    public ChestMenu(int height, Component title) {
        this.setTitle(title);
        if (height > 6) {
            this.height = 6;
        } else if (height < 1) {
            this.height = 1;
        } else {
            this.height = height;
        }
    }

    @Override
    protected MenuInventoryHolder createNewHolder() {
        return new MenuInventoryHolder(height, getTitle(), this);
    }
}
