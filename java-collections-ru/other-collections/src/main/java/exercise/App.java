package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// BEGIN
class App {


    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new LinkedHashMap<String, String>();
        Set<String> keysSet = new TreeSet<>();
        keysSet.addAll(map1.keySet());
        keysSet.addAll(map2.keySet());
        String[] keysArray = keysSet.toArray(new String[0]);

        for (String key : keysArray) {
            if (map1.keySet().contains(key) && map2.keySet().contains(key)) {
                if (map1.get(key).equals(map2.get(key))) {
                    result.put(key, "unchanged");
                } else {
                    result.put(key, "changed");
                }
            } else if (map1.keySet().contains(key)) {
                result.put(key, "deleted");
            } else {
                result.put(key, "added");
            }
        }

        return result;
    }
}
//END
