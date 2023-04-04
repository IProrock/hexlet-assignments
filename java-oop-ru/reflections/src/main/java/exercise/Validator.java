package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {

        Class<?> aClass = address.getClass();
        Field[] fields = aClass.getDeclaredFields();

        List<String> result = new ArrayList<>();

        for (Field field : fields) {

            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (Exception e) {
                    System.out.println("Error somemmm");
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {

        List<String> errorLst = new ArrayList<>();
        Map<String, List<String>> resultMap = new HashMap<>();
        String error1 = "length less than ";
        String error2 = "can not be null";

        Class<?> aClass = address.getClass();
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            System.out.println("Field: " + field.getName());
            errorLst.clear();

            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        errorLst.add(error2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("ErrLst1: " + errorLst);
            }

            if (field.isAnnotationPresent(MinLength.class)) {
                field.setAccessible(true);
                int limit;
                int actualFieldLength = 3;

                MinLength minLength = field.getAnnotation(MinLength.class);
                limit = minLength.minLength();

                try {
                    actualFieldLength = field.get(address).toString().length();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (actualFieldLength < limit) {
                    errorLst.add(error1 + limit);
                }


            }
            System.out.println("ErrList2: " + errorLst);
            if (!errorLst.isEmpty()) {

                resultMap.put(field.getName(), new ArrayList<>(errorLst));
            }
        }

        return resultMap;
    }
}
// END
