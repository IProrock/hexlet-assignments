package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
class App {

    public static void main(String[] args) {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        System.out.println("Res: " + Arrays.deepToString(enlargeArrayImage(image)));
    }

    public static String[][] enlargeArrayImage(String[][] image) {

        int oneLineLengthInit = image[0].length;
        int oneLineLengthFin = oneLineLengthInit * 2;

        String[] horizontalDoubleSymbols = Arrays.stream(image)
                .flatMap(row -> Arrays.stream(row))
                .flatMap(row -> Stream.of(row, row))
                .toArray(String[]::new);

        String[][] subResult = new String[oneLineLengthInit][oneLineLengthFin];
        int i = 0;
        for (int line = 0; line < oneLineLengthInit; line++) {
            for (int elem = 0; elem < oneLineLengthFin; elem++) {
                subResult[line][elem] = horizontalDoubleSymbols[i];
                i++;
            }
        }

        String[][] res = Arrays.stream(subResult)
                .flatMap(row -> Stream.of(row, row))
                .toArray(String[][]::new);

        return res;

    }
}
// END
