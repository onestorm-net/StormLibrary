package net.onestorm.library.paper.menu.element;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.onestorm.library.menu.Menu;

import java.util.Collection;

public interface TagResolverElement {

    Collection<TagResolver> getTagResolvers(Menu menu);

}
