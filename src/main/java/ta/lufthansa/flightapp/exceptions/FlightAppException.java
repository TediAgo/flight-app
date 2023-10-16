package ta.lufthansa.flightapp.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FlightAppException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;
    private HttpStatus status;

    public FlightAppException(HttpStatus status, String message) {
        this.message = message;
        this.status = status;
    }
}
