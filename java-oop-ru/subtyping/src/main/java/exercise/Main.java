package exercise;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        Map<String, String> initial = new HashMap<>();
//        initial.put("key", "10");
//
//        Map<String, String> clonedInitial = new HashMap<>();
//        clonedInitial.putAll(initial);
//
//        KeyValueStorage storage = new InMemoryKV(initial);
//
//        initial.put("key2", "value2");
//        System.out.println(storage.toMap() + "\n========\n" + clonedInitial);
//
//        Map<String, String> map = storage.toMap();
//        map.put("key2", "value2");
//        System.out.println(storage.toMap() + "\n=======\n" + clonedInitial);
        KeyValueStorage storage = new InMemoryKV(Map.of("foo", "bar", "bar", "zoo"));
        System.out.println(storage.toMap());
        storage = App.swapKeyValue(storage);
        System.out.println(storage.toMap());

    }
}
