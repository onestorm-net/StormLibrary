package net.onestorm.library.menu;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.IdentifiableElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public abstract class AbstractMenu implements Menu, TagResolverMenu {

    protected String title;
    protected final List<Element> elementList = new ArrayList<>();
    protected Collection<TagResolver> tagResolvers = new ArrayList<>();

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Element> getElements() {
        return elementList;
    }

    @Override
    public List<Element> getElementsByName(String name) {
        List<Element> result = new ArrayList<>();

        for (Element element : elementList) {
            if (element.getName().equalsIgnoreCase(name)) {
                result.add(element);
            }
        }

        return result;
    }

    @Override
    public Optional<Element> getElementById(String identifier) {
        Element result = null;

        for (Element element : elementList) {
            if (!(element instanceof IdentifiableElement IdentifiableElement)) {
                continue;
            }

            Optional<String> optionalId = IdentifiableElement.getIdentifier();

            if (optionalId.isEmpty() || !optionalId.get().equalsIgnoreCase(identifier)) {
                continue;
            }

            result = element;
            break; // id should be unique, just return the first element we find
        }

        return Optional.ofNullable(result);
    }

    @Override
    public void addElement(Element element) {
        elementList.add(element);
        element.register(this);
    }

    @Override
    public void addElements(List<Element> elements) {
        elementList.forEach(this::addElement);
    }

    @Override
    public void removeElement(Element element) {
        if (elementList.remove(element)) {
            element.unregister(this);
        }
    }

    @Override
    public void removeElementById(String identifier) {
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (!(element instanceof IdentifiableElement identifierElement)) {
                continue;
            }
            Optional<String> optionalId = identifierElement.getIdentifier();

            if (optionalId.isPresent() && optionalId.get().equalsIgnoreCase(identifier)) {
                iterator.remove();
                element.unregister(this);
                break; // id should be unique, just delete the first element we find
            }
        }
    }

    @Override
    public Collection<TagResolver> getTagResolvers() {
        return tagResolvers;
    }

    @Override
    public void setTagResolvers(Collection<TagResolver> resolvers) {
        this.tagResolvers = resolvers;
    }
}
