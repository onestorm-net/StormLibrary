package net.onestorm.library.menu;

import net.onestorm.library.common.context.UserContext;
import net.onestorm.library.menu.cell.Cell;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Menu {

    /**
     * Opens the menu for the specified user.
     *
     * @param user the user for whom the menu will be opened
     */
    default void open(User user) {
        final UserContext context = UserContext.of(user);
        open(context);
    }

    /**
     * Opens the menu for the specified user context.
     *
     * @param context the user context for which the menu will be opened
     */
    void open(UserContext context);

    /**
     * Opens the menu for the specified user with additional options.
     *
     * @param user the user for whom the menu will be opened
     * @param options a map of options that can be used by the menu/elements (e.g., page, tab to be opened)
     */
    @Deprecated
    default void open(User user, Map<String, Object> options) {
        final UserContext context = UserContext.of(user);
        open(context);
    }

    /**
     * Retrieves the context of the menu.
     *
     * @return the context associated with this menu
     */
    UserContext getContext();

    /**
     * Retrieves the menu options.
     *
     * @return a map containing the menu options
     */
    @Deprecated
    Map<String, Object> getOptions();

    /**
     * Retrieves the title of the menu, which can be formatted using the mini message format.
     *
     * @return the menu title
     */
    String getTitle();

    /**
     * Sets the title of the menu, which can be formatted using the mini message format.
     *
     * @param title the new title of the menu
     */
    void setTitle(String title);

    /**
     * Retrieves the owner of the menu, indicating for whom the menu was built and opened.
     *
     * @return the menu owner
     */
    @Deprecated
    User getOwner();

    /**
     * Retrieves the width of the menu.
     *
     * @return the menu width
     */
    int getWidth();

    /**
     * Retrieves the height of the menu.
     *
     * @return the menu height
     */
    int getHeight();

    /**
     * Retrieves the size of the menu, indicating the number of cells it contains.
     *
     * @return the menu size
     */
    int getSize();

    /**
     * Updates a specific cell within the menu.
     *
     * @param cell the cell to be updated
     */
    void updateCell(Cell cell);

    /**
     * Updates the entire menu.
     */
    void update();

    /**
     * Retrieves a list of all elements in the menu.
     *
     * @return a list of all elements
     */
    List<Element> getElements();

    /**
     * Retrieves a list of elements that have the specified name (type).
     *
     * @param name the name (type) of the elements to be retrieved
     * @return a list of elements with the specified name
     */
    List<Element> getElementsByName(String name);

    /**
     * Retrieves an element by its unique identifier.
     *
     * @param identifier the unique identifier of the element
     * @return an {@code Optional} containing the element if found, or an empty {@code Optional} if not found
     * @see net.onestorm.library.menu.element.IdentifiableElement
     */
    Optional<Element> getElementById(String identifier);

    /**
     * Adds an element to the menu.
     *
     * @param element the element to be added
     */
    void addElement(Element element);

    /**
     * Adds a list of elements to the menu.
     *
     * @param elements the list of elements to be added
     */
    void addElements(List<Element> elements);

    /**
     * Removes an element from the menu.
     *
     * @param element the element to be removed
     */
    void removeElement(Element element);

    /**
     * Removes an element from the menu by its unique identifier.
     *
     * @param identifier the unique identifier of the element to be removed
     */
    void removeElementById(String identifier);

    /**
     * Closes the menu.
     */
    void close();
}