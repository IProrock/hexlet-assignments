package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {


    public static String getForwardedVariables(String config) {

        String filtered = config.lines()
                .filter(line -> line.startsWith("environment"))
                .map(line -> line.replaceAll("environment=", ""))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.split(","))
                .flatMap(line -> Arrays.stream(line))
                .filter(line -> line.startsWith("X_FORWARDED"))
                .map(line -> line.replaceAll("X_FORWARDED_", ""))
                .collect(Collectors.joining(","));

        return filtered;


    }
}
//END
