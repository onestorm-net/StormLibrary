package net.onestorm.library.menu.element;

import java.util.Optional;

public abstract class AbstractElement implements Element, IdentifiableElement, PrioritizableElement {

    protected String identifier = null;
    protected int priority = 0;

    @Override
    public Optional<String> getIdentifier() {
        return Optional.ofNullable(identifier);
    }

    @Override
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
