package exercise.daytime;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class Night implements Daytime {
    private String name = "night";

    public String getName() {
        return name;
    }

    // BEGIN
    @PostConstruct
    public void postConstrMessage() {
        System.out.println("Post construct night");
    }

    @PreDestroy
    public void preDestroyMessage() {
        System.out.println("Pre destroy construct night");
    }
    // END
}
