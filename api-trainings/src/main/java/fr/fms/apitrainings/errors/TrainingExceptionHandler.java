package fr.fms.apitrainings.errors;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class TrainingExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handlerTrainingNotFoundException(RecordNotFoundException exception, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        String INCORRECT_REQUEST = "INCORRECT_REQUEST";
        ErrorResponse error = new ErrorResponse(INCORRECT_REQUEST, details);
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingHeaderInfoException.class)
    public final ResponseEntity<ErrorResponse> handlerInvalidException(RecordNotFoundException exception, WebRequest request){
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        String BAD_REQUEST = "BAD_REQUEST";
        ErrorResponse error = new ErrorResponse(BAD_REQUEST, details);
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}
