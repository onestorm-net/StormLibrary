package net.onestorm.library.menu;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Collection;

public interface TagResolverMenu {

    Collection<TagResolver> getTagResolvers();

    void setTagResolvers(Collection<TagResolver> resolvers);

}
