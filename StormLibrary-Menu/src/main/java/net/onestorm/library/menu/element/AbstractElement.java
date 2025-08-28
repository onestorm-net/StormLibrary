package net.onestorm.library.menu.element;

import net.onestorm.library.menu.Menu;

import java.util.Optional;

public abstract class AbstractElement implements Element, IdentifiableElement, PrioritizableElement {

    protected String identifier = null;
    protected int priority = 0;

    @Override
    public void register(Menu menu) {
        // Override me
    }

    @Override
    public void unregister(Menu menu) {
        // Override me
    }

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
