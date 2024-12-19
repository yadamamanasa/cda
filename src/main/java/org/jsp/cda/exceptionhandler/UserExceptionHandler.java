package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.InvalidUserEmailException;
import org.jsp.cda.exceptionclasses.NoUserFoundException;
import org.jsp.cda.exceptionclasses.UserNotFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
	
	@ExceptionHandler(NoUserFoundException.class)
	public ResponseEntity<?> noUserFoundException(NoUserFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No User Found in database").body("User not present in database").build());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e){
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().httpStatus(HttpStatus.BAD_REQUEST.value()).message("User Not Found...").body(e.getMessage()).build());
	}
	@ExceptionHandler(InvalidUserEmailException.class)
	public ResponseEntity<?> invalidUserEmailException(InvalidUserEmailException e){
		return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseStructure.builder().httpStatus(HttpStatus.BAD_REQUEST.value()).message("No User Found By Email in database...").body(e.getMessage()).build());
	}

}
