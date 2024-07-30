package net.onestorm.library.storage;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;

public interface StorageMap extends StorageElement {

    /**
     * Gets a child StorageMap in this StorageMap by using the path.
     * @param path Path of the StorageMap to get.
     * @return Requested StorageMap.
     */
    Optional<StorageMap> getMap(String path);

    /**
     * Sets a child StorageMap in this StorageMap by using the path.
     * @param path The path where the new StorageMap will be placed.
     * @param overwrite Should the StorageMap be overwritten when it already exists.
     * @return The set StorageMap.
     */
    StorageMap setMap(String path, boolean overwrite);

    /**
     * Sets a child StorageMap in this StorageMap by using the path.
     * <br>
     * NOTE: by default it will overwrite the existing map {@link StorageMap#setMap(String, boolean)}.
     * @param path The path where the new StorageMap will be placed.
     * @return The set StorageMap.
     */
    StorageMap setMap(String path);

    /**
     * Gets a child StorageList in this StorageMap by using the path.
     * <br>
     * NOTE: by default it will overwrite the existing list {@link StorageMap#setList(String, boolean)}.
     * @param path Path to the StorageList.
     * @return Requested StorageList.
     */
    Optional<StorageList> getList(String path);

    /**
     * Sets a child StorageList in this StorageMap by using the path.
     * @param path The path where the new StorageList will be placed.
     * @param overwrite Should the StorageList be overwritten when it already exists.
     * @return The set StorageList.
     */
    StorageList setList(String path, boolean overwrite);

    /**
     * Sets a child StorageList in this StorageMap by using the path.
     * @param path The path where the new StorageList will be placed.
     * @return The set StorageList.
     */
    StorageList setList(String path);

    /**
     * Gets a child object in this StorageMap by using the path.
     * @param path Path of the Object to get.
     * @return Requested Object.
     */
    Object get(String path);

    /**
     * Sets a child Object in this StorageMap by using the path.
     * @param path Path of the Object to set.
     * @param value The new Value to set.
     */
    void set(String path, Object value);

    /**
     * Sets a child Object in this StorageMap by using the path.
     * @param path Path of the Object to set.
     * @param value The new Value to set.
     * @param overwrite Should the value be overwritten when it already exists.
     */
    void set(String path, Object value, boolean overwrite);

    /**
     * Gets a child UUID in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Boolean to get.
     * @return Requested UUID.
     */
    Optional<UUID> getUuid(String path);

    /**
     * Gets a child Boolean in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Boolean to get.
     * @return Requested Boolean.
     */
    Optional<Boolean> getBoolean(String path);

    /**
     * Gets a child String in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the String to get.
     * @return Requested String.
     */
    Optional<String> getString(String path);

    /**
     * Gets a child Double in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Double to get.
     * @return Requested Double.
     */
    Optional<Double> getDouble(String path);

    /**
     * Gets a child BigDecimal in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the BigDecimal to get.
     * @return Requested BigDecimal.
     */
    Optional<BigDecimal> getBigDecimal(String path);

    /**
     * Gets a child BigInteger in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the BigInteger to get.
     * @return Requested BigInteger.
     */
    Optional<BigInteger> getBigInteger(String path);

    /**
     * Gets a child Float in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Float to get.
     * @return Requested Float.
     */
    Optional<Float> getFloat(String path);

    /**
     * Gets a child Long in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Long to get.
     * @return Requested Long.
     */
    Optional<Long> getLong(String path);

    /**
     * Gets a child Short in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Short to get.
     * @return Requested Short.
     */
    Optional<Short> getShort(String path);

    /**
     * Gets a child Integer in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Integer to get.
     * @return Requested Integer.
     */
    Optional<Integer> getInteger(String path);

    /**
     * Gets a child Byte in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Byte to get.
     * @return Requested Byte.
     */
    Optional<Byte> getByte(String path);

    /**
     * Gets a child Character in this StorageMap by using the path.
     * <p>
     * The (Optional) result will be empty when there was no value found or when the value is not castable/parsable to the requested type.
     * @param path Path of the Character to get.
     * @return Requested Character.
     */
    Optional<Character> getCharacter(String path);

    /**
     * Performs a given action for each map entry.
     * @param action the action.
     */
    void forEach(BiConsumer<String, Object> action);



}
