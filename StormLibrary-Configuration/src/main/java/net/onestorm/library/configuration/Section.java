package net.onestorm.library.configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Section {

    /**
     * Gets the root configuration implementation for this section.
     * @return Configuration.
     */
    Configuration getRoot();

    /**
     * Gets the parent section of this section.
     * @return Section.
     */
    Section getParent();

    /**
     * Gets a child Section in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Section.
     */
    Optional<Section> getSection(String path);

    /**
     * Creates a new child Section in this Section.
     * @param path Path of the location where to create the new Section.
     * @return The created Section.
     */
    Section createSection(String path);

    /**
     * Gets a child object in this Section by using the path.
     * @param path Path of the Object to get.
     * @return Requested Object.
     */
    Object get(String path);

    /**
     * Sets a child Object in this Section by using the path.
     * @param path Path of the Object to set.
     * @param value The new Value to set.
     */
    void set(String path, Object value);

    /**
     * Sets a child Object in this Section by using the path.
     * @param path Path of the Object to set.
     * @param value The new Value to set.
     * @param overwrite Should the value be overwritten when it already exists.
     */
    void set(String path, Object value, boolean overwrite);

    /**
     * Gets this Section as an (unmodifiable) Map.
     * @return Section as Map
     */
    Map<String, Object> asMap();

    /**
     * Gets this Section as an (unmodifiable) List.
     * @return Section as List
     */
    List<Object> asList();

    /**
     * Gets a child List in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested List.
     */
    Optional<List<Object>> getList(String path);

    /**
     * Gets a child Boolean in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Boolean.
     */
    Optional<Boolean> getBoolean(String path);

    /**
     * Gets a child String in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested String.
     */
    Optional<String> getString(String path);

    /**
     * Gets a child Double in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Double.
     */
    Optional<Double> getDouble(String path);

    /**
     * Gets a child BigDecimal in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested BigDecimal.
     */
    Optional<BigDecimal> getBigDecimal(String path);

    /**
     * Gets a child BigInteger in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested BigInteger.
     */
    Optional<BigInteger> getBigInteger(String path);

    /**
     * Gets a child Float in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Float.
     */
    Optional<Float> getFloat(String path);

    /**
     * Gets a child Long in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Long.
     */
    Optional<Long> getLong(String path);

    /**
     * Gets a child Short in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Short.
     */
    Optional<Short> getShort(String path);

    /**
     * Gets a child Integer in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Integer.
     */
    Optional<Integer> getInteger(String path);

    /**
     * Gets a child Byte in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Byte.
     */
    Optional<Byte> getByte(String path);

    /**
     * Gets a child Character in this Section by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type
     * @param path Path of the Section to get.
     * @return Requested Character.
     */
    Optional<Character> getCharacter(String path);

}
