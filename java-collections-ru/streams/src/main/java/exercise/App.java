package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class App {

    public static Integer getCountOfFreeEmails(List<String> emails) {

        int totalCount = emails.size();

        long criteriaCount = emails.stream()
                .filter(email -> !email.endsWith("gmail.com"))
                .filter(email -> !email.endsWith("yandex.ru"))
                .filter(email -> !email.endsWith("hotmail.com"))
                .collect(Collectors.counting());

        int count = totalCount - (int) criteriaCount;

        return count;
    }
}
// END
