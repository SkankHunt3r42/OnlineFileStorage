package onlineFileStorage.root.app.FileStorage.handlers;

import onlineFileStorage.root.app.FileStorage.handlers.exception.ExistingUsernameException;
import onlineFileStorage.root.app.FileStorage.handlers.exception.JwtProblemException;
import onlineFileStorage.root.app.FileStorage.handlers.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExcHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> response_1(JwtProblemException exc){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.UNAUTHORIZED.value(),exc.getMessage(),new Date()
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> response_2(ExistingUsernameException exc){
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                HttpStatus.LOCKED.value(),exc.getMessage(),new Date()
        );
        return new ResponseEntity<>(exceptionResponse,HttpStatus.UNAUTHORIZED);
    }

}
