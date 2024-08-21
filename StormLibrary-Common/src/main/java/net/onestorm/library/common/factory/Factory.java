package net.onestorm.library.common.factory;

import net.onestorm.library.common.factory.context.BuildContext;

public interface Factory<P> {

    /**
     * Builds a "product" with the given configurations. The name from the builder is gained
     * from {@link BuildContext#getName}
     * @param storage The StorageMap configuration
     * @return The "product"
     */
    P build(BuildContext context);

    /**
     * Register a builder for this Factory
     * @param builder the builder
     */
    void registerBuilder(Builder<P> builder);

    /**
     * Unregister a builder for this Factory
     * @param builder the builder
     */
    void unregisterBuilder(Builder<P> builder);

}
