package fr.fms.apitrainings.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ImageUploadMaxSizeException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorResponse> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getLocalizedMessage());
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", details);
//        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ErrorResponse("error", details));
        return  new ResponseEntity<>(error, HttpStatus.EXPECTATION_FAILED);
    }
}
