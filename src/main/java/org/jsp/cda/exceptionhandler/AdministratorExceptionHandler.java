package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.NoAdministratorFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdministratorExceptionHandler {
	
	@ExceptionHandler(NoAdministratorFoundException.class)
	public ResponseEntity<?> noDepartmentFoundException(NoAdministratorFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Administrator Found in database").body("Administrator not present in database").build());
	}

}
