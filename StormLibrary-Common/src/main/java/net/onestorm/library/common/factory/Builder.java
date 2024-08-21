package net.onestorm.library.common.factory;

import net.onestorm.library.common.factory.context.BuildContext;

public interface Builder<P> {

    /**
     * Gets the name of this builder. Used for {@link Factory} implementations
     * @return the name of the builder
     */
    String getName();

    /**
     * Builds a "product" with the given context
     * @param context the context
     * @return The "product"
     */
    P build(BuildContext context);

}
