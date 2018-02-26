package dev5.chermenin.rest.controller.exceptionController;

import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;


/**
 * Created by Ancarian on 21.12.2017.
 */

@RestControllerAdvice
@ResponseBody
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ExceptionDto exceptionDto = new ExceptionDto(ex);
        return new ResponseEntity<>(exceptionDto, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ExistsException.class})
    protected ResponseEntity<Object> handleExists(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ExceptionDto exceptionDto = new ExceptionDto(ex);
        return new ResponseEntity<>(exceptionDto, headers, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgument(RuntimeException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ExceptionDto exceptionDto = new ExceptionDto(ex);
        return new ResponseEntity<>(exceptionDto, headers, HttpStatus.BAD_REQUEST);
    }

    private static class ExceptionDto {
        private Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        private String methodName;
        private String message;

        public ExceptionDto(Exception ex) {
            this.methodName  = ex.getMessage();
            this.message = ex.getStackTrace()[0].getMethodName();
        }

        public Timestamp getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Timestamp timestamp) {
            this.timestamp = timestamp;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }

}