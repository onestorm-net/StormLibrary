package net.onestorm.library.common.factory;

import net.onestorm.library.common.context.BuildContext;

public interface Factory<P> {

    /**
     * Builds a "product" using the specified context. The builder's name is retrieved
     * from {@link BuildContext#getName}.
     *
     * @param context the context used to build the "product"
     * @return the built "product"
     * @throws BuildException if the factory or builder is unable to create a "product" with the given context
     */
    P build(BuildContext context);

    /**
     * Registers a builder with this factory.
     *
     * @param builder the builder to be registered
     */
    void registerBuilder(Builder<P> builder);

    /**
     * Unregisters a builder from this factory.
     *
     * @param builder the builder to be unregistered
     */
    void unregisterBuilder(Builder<P> builder);

}
