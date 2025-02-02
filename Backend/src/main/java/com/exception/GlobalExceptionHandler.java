package com.exception;
 
import java.util.Date;
 
import org.springframework.http.HttpStatus;
 
import org.springframework.http.ResponseEntity;
 
import org.springframework.web.bind.annotation.ControllerAdvice;
 
import org.springframework.web.bind.annotation.ExceptionHandler;
 
import org.springframework.web.context.request.WebRequest;
 
@ControllerAdvice
 
public class GlobalExceptionHandler {
 
	@ExceptionHandler(ResourceNotFoundException.class)
 
        public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
 
         	ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
 
         	return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
 
    	}
 
        @ExceptionHandler(Exception.class)
 
        public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
 
	        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
 
	        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
 
	    }
 
    @ExceptionHandler(InvalidDetailsException.class)
 
    public ResponseEntity<ErrorDetails> handleInvalidDetailsException(InvalidDetailsException ex, WebRequest request) {
 
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
 
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
 
    }
 
    @ExceptionHandler(DataAlreadyExistsException.class)
 
    public ResponseEntity<ErrorDetails> handleDataAlreadyExistsException(DataAlreadyExistsException ex, WebRequest request) {
 
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
 
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
 
    }
 
 
    
 
	}
 
 
 
 