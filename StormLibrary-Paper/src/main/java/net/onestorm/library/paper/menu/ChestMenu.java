package net.onestorm.library.paper.menu;


public class ChestMenu extends PaperMenu {

    private static final int MENU_WIDTH = 9;

    private int size;
    private int height;

    public ChestMenu(int size) {
        setSize(size);
    }

    @Override
    public int getWidth() {
        return MENU_WIDTH;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size < 0 || size > 54 || size % 9 != 0) {
            throw new IllegalArgumentException("Illegal inventory size");
        }
        this.size = size;
        this.height = size / 9;
    }

    @Override
    protected MenuHolder createMenuHolder() {
        String title = getTitle();
        if (title == null || title.isEmpty()) {
            return new MenuHolder(this, size);
        }
        return new MenuHolder(this, size, createTitle());
    }
}
