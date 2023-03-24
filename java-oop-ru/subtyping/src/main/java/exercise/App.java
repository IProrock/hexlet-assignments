package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage dbSample) {
        Map<String, String> mapFromDB = dbSample.toMap();
        Map<String, String> tempMap = new HashMap<>();
        Set<String> keysToRemove = mapFromDB.keySet();

        for (Map.Entry<String, String> entry : mapFromDB.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            tempMap.put(value, key);
        }

        for (String tst : keysToRemove) {
            dbSample.unset(tst);
        }


        for (Map.Entry<String, String> entry : tempMap.entrySet()) {
            dbSample.set(entry.getKey(), entry.getValue());
        }

        return dbSample;

    }
}
// END
