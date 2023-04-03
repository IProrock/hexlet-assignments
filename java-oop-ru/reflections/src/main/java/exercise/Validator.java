package exercise;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Address adress) {

        Class<?> aClass = adress.getClass();
        Field[] fields = aClass.getDeclaredFields();

        List<String> result = new ArrayList<>();

        for (Field field : fields) {

            Annotation[] annotations = field.getDeclaredAnnotations();
            List<String> annotationsStr = new ArrayList<>();
            for (Annotation annotation : annotations) {
                annotationsStr.add(annotation.toString());
            }
            if (annotationsStr.contains("@exercise.NotNull()")) {
                field.setAccessible(true);
                Object obj = new Object();
                try {
                    if (field.get(adress) == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
        }
        return result;
    }
}
// END
