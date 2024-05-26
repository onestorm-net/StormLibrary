## Storage
This storage module is based on the configuration design of Bukkit, but with defaults removed and nullable getters are replaced with Optional getters.

This module is an updated version of the old configuration module. The main changes are that it is renamed to Storage, as I want to use in for mongodb databases in the future. Also the issue where List objects were saved as Maps/Object is fixed by adding its own "Section" object.

### Load, set and save
```java
FileStorage storage = new JsonStorage();

File file = new File("application/config.json");

try {
    // load from file
    storage.load(file);
    // set a value with a path (creates missing maps when not existing)
    storage.set("test1.test2.test3", "Hello World!");
    // set a value if not existing (by setting overwrite to false) 
    storage.set("test1.test2.test3", "Hello Default", false);
    // save a value
    storage.save(file);
} catch (IOException e) {
    e.printStackTrace();
}
```

### Storage/StorageMap (key->value collection)
```java
FileStorage storage = new JsonStorage();

// Getters in Storage/StorageMap
Optional<StorageMap> optionalMap = storage.getMap("path.to.map");
Optional<StorageList> optionalList = storage.getList("path.to.list");
Object value = storage.get("path.to.value"); // nullable, mainly used internally
boolean booleanValue = storage.getBoolean("path.to.boolean").orElse(false);
String stringValue = storage.getString("path.to.string").orElse("value");
double doubleValue = storage.getDouble("path.to.double").orElse(1.5D);
BigDecimal bigDecimal = storage.getBigDecimal("path.to.big-decimal").orElse(new BigDecimal("1.5"));
BigInteger bigInteger = storage.getBigInteger("path.to.big-integer").orElse(new BigInteger("10_000_000_000"));
float floatValue = storage.getFloat("path.to.float").orElse(1.5F);
long longValue = storage.getLong("path.to.long").orElse(10_000_000_000L);
short shortValue = storage.getShort("path.to.short").orElse((short) 0xFFFF);
int intValue = storage.getInteger("path.to.int").orElse(1_000_000_000);
byte byteValue = storage.getByte("path.to.byte").orElse((byte) 0xFF);
char charValue = storage.getCharacter("path.to.char").orElse('A');

// Setters in Storage/StorageMap
StorageMap map = storage.setMap("path.to.map");
StorageMap map = storage.setMap("path.to.map", false); // stops an existing map from being overwritten
StorageList list = storage.setList("path.to.list");
StorageList list = storage.setList("path.to.list", false); // stops an existing list from being overwritten
storage.set("path.to.value", "Hello World");
storage.set("path.to.value", "Hello World", false); // stops an existing value from being overwritten
```

### StorageList (value collection)
```java
FileStorage storage = new JsonStorage();
StorageList storageList = storage.setList("path.to.some.list");

// Adders in Storage/StorageList
StorageMap map = storageList.addMap();
StorageList list = storageList.addList();
storageList.add("Hello World")
```

### Todo
- [x] Add Options to Configuration (Root Section);
- [ ] Add more List methods (getStringList, getIntegerList);
- [ ] Add Jetbrains annotations (NotNull, Nullable) to all methods.
- [x] Redo deserialization/serialization in the Json implementation;
- [ ] Add a yaml implementation, as it is more readable than json and supports comments;