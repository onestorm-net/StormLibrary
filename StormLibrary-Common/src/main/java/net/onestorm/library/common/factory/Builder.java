package net.onestorm.library.common.factory;

import net.onestorm.library.common.context.BuildContext;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Builder<P> {

    /**
     * Gets the name of this builder. Used for {@link Factory} implementations
     *
     * @return the name of the builder
     */
    String getName();

    /**
     * Builds a "product" with the given context
     *
     * @param context the context
     * @return The "product"
     * @throws BuildException if the builder is unable to create a "product" with the given context
     */
    P build(BuildContext context);

    /**
     * Creates a new instance of {@link Builder} using the specified name and a function
     * that defines how to build the product.
     *
     * @param name     the name of the builder
     * @param function a function that takes a {@link BuildContext} and returns the product
     * @param <P>     the type of product produced by the builder
     * @return a new {@link Builder} instance
     */
    static <P> Builder<P> of(String name, Function<BuildContext, P> function) {
        return new Builder<>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public P build(BuildContext context) {
                return function.apply(context);
            }
        };
    }

    /**
     * Creates a new instance of {@link Builder} using the specified name and a supplier
     * that provides the product without requiring a context.
     *
     * @param name     the name of the builder
     * @param supplier a supplier that provides the product
     * @param <P>     the type of product produced by the builder
     * @return a new {@link Builder} instance
     */
    static <P> Builder<P> of(String name, Supplier<P> supplier) {
        return new Builder<>() {
            @Override
            public String getName() {
                return name;
            }

            @Override
            public P build(BuildContext context) {
                return supplier.get();
            }
        };
    }
}
