package net.onestorm.library.menu.util;

import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.Factory;
import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.build.AbstractMenuBuilder;
import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

import java.util.function.Supplier;

/**
 * Utility class for creating and managing menu builders in a flexible and dynamic way.
 * The {@code MenuUtil} class provides a static method to generate builders for menus
 * using a {@link Supplier<Menu>} for the menu creation, without the need to create
 * separate builder classes for each menu type.
 *
 * <p>This class allows developers to create and register builders for various types
 * of menus by simply passing a factory for elements, the menu name, and a supplier
 * that provides an instance of the specific {@link Menu}.</p>
 *
 * <p>The class also contains an inner class {@link SupplierBasedMenuBuilder} that
 * implements the dynamic building of menus using the provided supplier.</p>
 *
 * <p>This class cannot be instantiated as it is purely a utility class.</p>
 */
public class MenuUtil {

    private MenuUtil() {}

    /**
     * Creates a dynamic {@link Builder} for a menu using a provided {@link Factory<Element>} for element creation,
     * a menu name, and a {@link Supplier<Menu>} that supplies an instance of the specific {@link Menu}.
     *
     * <p>This method is a shortcut to quickly register builders for different menus by using suppliers.
     * The builder created by this method can be registered in the {@link net.onestorm.library.common.factory.Factory}
     * for later use.</p>
     *
     * @param elementFactory the factory responsible for creating {@link Element} instances for the menu
     * @param name the name of the menu, which will be used to identify this builder
     * @param menuSupplier the supplier that provides the specific {@link Menu} instance when building
     * @return a {@link Builder} that can build the menu defined by the supplied {@link Supplier<Menu>}
     */
    public static Builder<Menu> builder(Factory<Element> elementFactory, String name, Supplier<Menu> menuSupplier) {
        return new SupplierBasedMenuBuilder(elementFactory, name, menuSupplier);
    }

    private static class SupplierBasedMenuBuilder extends AbstractMenuBuilder {

        private final String name;
        private final Supplier<Menu> menuSupplier;

        public SupplierBasedMenuBuilder(Factory<Element> elementFactory, String name, Supplier<Menu> menuSupplier) {
            super(elementFactory);
            this.name = name;
            this.menuSupplier = menuSupplier;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        protected Menu createMenu(StorageMap configuration) {
            return menuSupplier.get();
        }
    }

}
