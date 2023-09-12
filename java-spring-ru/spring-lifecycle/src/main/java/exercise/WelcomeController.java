package exercise;

import exercise.daytimes.Daytime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {
    @Autowired
    Meal meal;

    @Autowired
    Daytime getDayTime;

    @GetMapping(path = "/daytime")
    public String welcome() {

        String dayTimeName = getDayTime.getName();

        String result = "It is " + dayTimeName + ". Enjoy your " + meal.getMealForDaytime(dayTimeName);

        return result;
    }
}
// END
