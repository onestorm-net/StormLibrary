package net.onestorm.library.menu;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.menu.element.IdentifiableElement;

import java.util.ArrayList;
import java.util.Collection;
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
    }

    @Override
    public void addElements(List<Element> elements) {
        elementList.addAll(elements);
    }

    @Override
    public void removeElement(Element element) {
        elementList.remove(element);
    }

    @Override
    public void removeElementById(String identifier) {
        elementList.removeIf(element -> {
            if (!(element instanceof IdentifiableElement identifierElement)) {
                return false; // continue
            }
            Optional<String> optionalId = identifierElement.getIdentifier();
            return optionalId.isPresent() && optionalId.get().equalsIgnoreCase(identifier);
        });
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
