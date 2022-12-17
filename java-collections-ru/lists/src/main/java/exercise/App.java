package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String symbols, String word) {

        String[] wordElementsArray = word.toLowerCase().split("");
        String[] symbolsArray = symbols.split("");
        List<String> symbolsList = new ArrayList<String>(Arrays.asList(symbolsArray));

        for (String element : wordElementsArray) {
            if (symbolsList.contains(element)) {
                symbolsList.remove(element);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
