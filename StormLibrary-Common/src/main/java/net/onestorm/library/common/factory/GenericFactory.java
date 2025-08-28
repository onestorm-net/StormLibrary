package net.onestorm.library.common.factory;


import net.onestorm.library.common.context.BuildContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GenericFactory<P> implements Factory<P> {

    private final Map<String, Builder<P>> builderMap = new HashMap<>();

    @Override
    public P build(BuildContext context) {
        String name = context.getName();

        Builder<P> builder = builderMap.get(name);
        if (builder == null) {
            throw new BuildException("Could not find a builder with the name: " + name);
        }

        P product;
        try {
            product = builder.build(context);
        } catch (Exception e) {
            throw new BuildException("Uncaught error while building a product: " + name, e);
        }

        return product;
    }

    @Override
    public void registerBuilder(Builder<P> builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);

        if (builderMap.containsKey(name)) {
            throw new IllegalArgumentException("There already exists a builder with this name: " + name);
        }
        builderMap.put(name, builder);
    }

    @Override
    public void unregisterBuilder(Builder<P> builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);
        builderMap.remove(name);
    }
}
