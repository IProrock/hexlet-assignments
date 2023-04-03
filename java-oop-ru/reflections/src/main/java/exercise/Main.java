package exercise;

import exercise.Address;
import exercise.Validator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Russia", "Ufa", "Lenina", "54", null);
        List<String> result1 = Validator.validate(address1);
        List<String> expected1 = List.of();
        System.out.println("Res 1: " + result1);
        System.out.println("Expected 1: " + expected1);

        Address address2 = new Address(null, "London", "1-st street", "5", "1");
        List<String> result2 = Validator.validate(address2);
        List<String> expected2 = List.of("country");
        System.out.println("Res 2: " + result2);
        System.out.println("Expected 2: " + expected2);

        Address address3 = new Address("USA", null, null, null, "1");
        List<String> result3 = Validator.validate(address3);
        List<String> expected3 = List.of("city", "street", "houseNumber");
        System.out.println("Res 3: " + result3);
        System.out.println("Expected 3: " + expected3);
    }
}
