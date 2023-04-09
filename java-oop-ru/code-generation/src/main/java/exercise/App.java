package exercise;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) {
        String carAsString = car.serialize();
        try{
            Files.writeString(path, carAsString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path ptFile) {
        try {
            Car returnCar = Car.unserialize(Files.readString(ptFile));
            return returnCar;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
// END
