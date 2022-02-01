package account;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.Map;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) {

        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(Map.of("message", "1Bad Request", "error","Bad Request","status", 400), HttpStatus.BAD_REQUEST);
        }

        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(Map.of("message", "2Method not allowed", "error","Bad Request","status", 400), HttpStatus.METHOD_NOT_ALLOWED);
        }

        if (ex instanceof ConstraintViolationException) {
            return new ResponseEntity<>(Map.of("message", "3Bad Request", "error","Bad Request","status", 400), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Map.of("message", "Password length must be 12 chars minimum!",
                "error","Bad Request",
                "status", 400,
        "path", "/api/auth/changepass"), HttpStatus.BAD_REQUEST);
    }
}
