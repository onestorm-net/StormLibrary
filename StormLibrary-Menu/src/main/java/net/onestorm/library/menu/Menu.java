package net.onestorm.library.menu;

import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.user.User;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Menu {

    /**
     * Opens the menu for the given user
     * @param user the user
     */
    void open(User user);

    /**
     * Opens the menu for the given user
     * @param user the user
     * @param options extra options the menu/elements can use (what page, tab should be opened)
     */
    void open(User user, Map<String, Object> options);

    /**
     * Gets the menu options
     * @return the options
     */
    Map<String, Object> getOptions();

    /**
     * Gets the title, title can be formatted with the mini message format
     * @return the title
     */
    String getTitle();

    /**
     * Sets the title, title can be formatted with the mini message format
     * @param title the title
     */
    void setTitle(String title);

    /**
     * Gets the menu owner, for who was the menu build and opened
     * @return the menu owner
     */
    User getOwner();

    /**
     * Gets the width of the menu
     * @return the menu width
     */
    int getWidth();

    /**
     * Gets the height of the menu
     * @return the menu height
     */
    int getHeight();

    /**
     * Gets the size of the menu. The amount of cells in this menu.
     * @return the size of the menu.
     */
    int getSize();

    /**
     * Updates an existing cell
     * @param cell the cell
     */
    void updateCell(Cell cell);

    /**
     * Updates the full menu
     */
    void update();

    /**
     * Gets a list of all elements
     * @return element list
     */
    List<Element> getElements();

    /**
     * Gets a list of elements which have the given name (element type)
     * @param name The element name (type)
     * @return The elements found with the given name
     */
    List<Element> getElementsByName(String name);

    /**
     * Gets an element by its unique identifier.
     * @param identifier the element identifier.
     * @return The element found with the given identifier.
     * @see net.onestorm.library.menu.element.IdentifiableElement
     */
    Optional<Element> getElementById(String identifier);

    /**
     * Adds an element to this menu
     * @param element the element
     */
    void addElement(Element element);

    /**
     * Adds a list of elements to this menu
     * @param elements element list
     */
    void addElements(List<Element> elements);

    /**
     * Removes an element from this menu
     * @param element the element
     */
    void removeElement(Element element);

    /**
     * Removes an element from this menu by its unique identifier
     * @param identifier the element identifier.
     */
    void removeElementById(String identifier);

    /**
     * Close the menu
     */
    void close();

}
