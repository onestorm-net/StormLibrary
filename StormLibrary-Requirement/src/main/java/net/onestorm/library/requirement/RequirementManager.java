package net.onestorm.library.requirement;

import net.onestorm.library.configuration.Section;

import java.util.List;

public interface RequirementManager {

    /**
     * Register a requirement builder.
     * @param builder The requirement builder.
     * @throws IllegalArgumentException Throws when a builder with the same name exists.
     */
    void registerBuilder(RequirementBuilder builder);

    /**
     * Unregister a requirement builder.
     * @param builder The requirement builder.
     */
    void unregisterBuilder(RequirementBuilder builder);

    /**
     * Creates a list of requirements from a configuration section.
     * <p>
     * Any configuration mistakes will be replaced with "Fallback" requirements, so user will not bypass requirement.
     * @param configuration The configuration section.
     * @return The list with created requirements.
     */
    List<Requirement> getRequirements(Section configuration);

}
