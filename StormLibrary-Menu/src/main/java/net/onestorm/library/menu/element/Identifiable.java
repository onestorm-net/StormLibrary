package net.onestorm.library.menu.element;

import java.util.Optional;

public interface Identifiable {

    Optional<String> getIdentifier();

    void setIdentifier(String identifier);

}
