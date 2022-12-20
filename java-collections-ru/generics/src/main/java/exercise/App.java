package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {


    public static List<Map<String, String>> findWhere(List<Map<String, String>> listBooks, Map<String, String> crit) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book: listBooks) {
            boolean ifMatch = true;
            for (Map.Entry<String, String> field: crit.entrySet()) {
                if (!field.getValue().equals(book.get(field.getKey()))) {
                    ifMatch = false;
                }
            }
            if (ifMatch) {
                result.add(book);
            }
        }
        return result;

    }
}
//END
