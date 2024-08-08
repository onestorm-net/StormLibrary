package net.onestorm.library.menu.element;

import java.util.Optional;

public interface IdentifiableElement {

    /**
     * Retrieves the element's identifier.
     *
     * @return an {@code Optional} containing the identifier if present, or an empty {@code Optional} if not present
     */
    Optional<String> getIdentifier();

    /**
     * Sets the element's identifier.
     *
     * @param identifier the identifier to set
     */
    void setIdentifier(String identifier);
}
