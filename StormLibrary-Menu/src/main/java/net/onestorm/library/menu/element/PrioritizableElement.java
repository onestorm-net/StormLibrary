package net.onestorm.library.menu.element;

public interface PrioritizableElement {

    /**
     * Gets the elements priority, the highest prioritized element will be shown. Should return 0 as default.
     * @return the priority
     */
    int getPriority();

    /**
     * Sets the elements priority
     * @param priority the priority
     */
    void setPriority(int priority);

}
