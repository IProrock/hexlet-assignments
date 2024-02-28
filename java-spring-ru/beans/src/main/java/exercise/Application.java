package exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import exercise.daytime.Daytime;
import exercise.daytime.Day;
import exercise.daytime.Night;

// BEGIN
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
// END

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @Scope("prototype")
    @Bean
    public Daytime getDayTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        int hour = localDateTime.getHour();

        if (hour >= 6 && hour <= 22) {
            return new Day();
        }

        return new Night();

    }
    // END
}
