package exercise;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private Map<String, String> mapa;

    public InMemoryKV(Map<String, String> initialMapa) {
        this.mapa = new HashMap<>();
        for (Map.Entry<String, String> entry : initialMapa.entrySet()) {
            this.mapa.put(entry.getKey(), entry.getValue());
        }
    }


    public void set(String key, String value) {
        this.mapa.put(key, value);
    }


    public void unset(String key) {
        this.mapa.remove(key);

    }


    public String get(String key, String defaultValue) {
        if (mapa.get(key) == null) {
            return defaultValue;
        }
        return this.mapa.get(key);
    }


    public Map<String, String> toMap() {
        Map<String, String> newMap = new HashMap<String, String>();
        newMap.putAll(this.mapa);
        return newMap;
    }
}
// END
