package exercise;

import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        String carString = "";
        ObjectMapper json = new ObjectMapper();
        try{
            carString = json.writeValueAsString(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return carString;
    }

    public static Car unserialize(String jsonCar) {

        ObjectMapper objectMapper = new ObjectMapper();

        try{
            Car result = objectMapper.readValue(jsonCar, Car.class);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    // END
}
