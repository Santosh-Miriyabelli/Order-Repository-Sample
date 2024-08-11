package com.sample.orderService.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sample.orderService.response.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	  public ResponseEntity<Response> handleResourceNotFoundException(ResourceNotFoundException ex) {
		 log.error("Exception Occured"+ex,ex.fillInStackTrace());
		 Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }

	  @ExceptionHandler(OrderNotFoundException.class)
	  public ResponseEntity<Response> orderNotFoundException(OrderNotFoundException ex) {
		  log.error("Exception Occured"+ex,ex.fillInStackTrace());
		 Response errorResponse = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	    }
	  
	  
	  @ExceptionHandler(Exception.class)
	  public ResponseEntity<Response> internalServerException(Exception ex) {
		  log.error("Exception Occured"+ex,ex.fillInStackTrace());
		 Response errorResponse = new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "!!Internal Server Error.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	    }
}
