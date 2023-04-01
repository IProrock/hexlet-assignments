package exercise;

import java.io.IOException;

// BEGIN
public class NegativeRadiusException extends Exception {
    public NegativeRadiusException() {
        super();
    }

    public NegativeRadiusException(String message) {
        super(message);
    }

    public NegativeRadiusException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeRadiusException(Throwable cause) {
        super(cause);
    }

}
// END
