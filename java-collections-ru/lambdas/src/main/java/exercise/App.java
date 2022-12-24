package exercise;

import java.util.Arrays;
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

//        String[] horizontalDoubleSymbols = Arrays.stream(image)
//                .flatMap(row -> Arrays.stream(row))
//                .flatMap(row -> Stream.of(row, row))
//                .toArray(String[]::new);

//        String[][] subResult = new String[oneLineLengthInit][oneLineLengthFin];
//        int i = 0;
//        for (int line = 0; line < oneLineLengthInit; line++) {
//            for (int elem = 0; elem < oneLineLengthFin; elem++) {
//                subResult[line][elem] = horizontalDoubleSymbols[i];
//                i++;
//            }
//        }

        String[][] subResult = Arrays.stream(image)
                .map(row -> duplicateItems(row))
                .toArray(String[][]::new);

        String[][] res = Arrays.stream(subResult)
                .flatMap(row -> Stream.of(row, row))
                .toArray(String[][]::new);

        return res;

    }

    public static String[] duplicateItems(String[] row) {
        return Arrays.stream(row)
                .flatMap(e -> Stream.of(e, e))
                .toArray(out -> new String[out]);
    }
}
// END
