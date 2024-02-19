## Configuration
This configuration module is based on the configuration design of Bukkit, but with defaults removed and nullable getters are replaced with Optional getters.

### Load, set and save
```java
FileConfiguration configuration = new JsonConfiguration();

File file = new File("application/config.json");

try {
    // load from file
    configuration.load(file);
    // set a value with a path (creates sections when not existing)
    configuration.set("test1.test2.test3", "Hello World!");
    // set a value if not existing (by setting overwrite to false) 
    configuration.set("test1.test2.test3", "Hello Default", false);
    // save a value
    configuration.save(file);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Sections
```java
FileConfiguration configuration = new JsonConfiguration();

// creates a section and all the parent sections when not existing
Section section = configuration.createSection("create.section.here");
// get a section
Optional<Section> getSection("get.section.here");
```

### Getters
```json
FileConfiguration configuration = new JsonConfiguration();

boolean booleanValue = configuration.getBoolean("path.to.boolean").orElse(false);
String stringValue = configuration.getString("path.to.string").orElse("value");
double doubleValue = configuration.getDouble("path.to.double").orElse(1.5D);
BigDecimal bigDecimal = configuration.getBigDecimal("path.to.big-decimal").orElse(new BigDecimal("1.5"));
BigInteger bigInteger = configuration.getBigInteger("path.to.big-integer").orElse(new BigInteger("10_000_000_000"));
float floatValue = configuration.getFloat("path.to.float").orElse(1.5F);
long longValue = configuration.getLong("path.to.long").orElse(10_000_000_000L);
short shortValue = configuration.getShort("path.to.short").orElse((short) 0xFFFF);
int intValue = configuration.getInteger("path.to.int").orElse(1_000_000_000);
byte byteValue = configuration.getByte("path.to.byte").orElse((byte) 0xFF);
char charValue = configuration.getCharacter("path.to.char").orElse('A');
```

### Limitations
In the current json implementation arrays are not fully supported, when deserialized they will be made into a section (object) with as key an index. When serialized it will be kept as an object. In the future sections will be redone to support arrays and objects while keeping the current root/parent structure.

### Todo
- [ ] Add Options to Configuration (Root Section);
- [ ] Add more List methods (getStringList, getIntegerList);
- [ ] Add Jetbrains annotations (NotNull, Nullable) to all methods.
- [ ] Redo deserialization/serialization in the Json implementation;
- [ ] Add condition checks for methods parameters in the Json implementation;
- [ ] Add a yaml implementation, as it is more readable than json and supports comments;