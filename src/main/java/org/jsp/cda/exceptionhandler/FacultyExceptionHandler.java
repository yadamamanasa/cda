package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.InvalidFacultyIdException;
import org.jsp.cda.exceptionclasses.NoFacultyFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FacultyExceptionHandler {
	
	@ExceptionHandler(NoFacultyFoundException.class)
	public ResponseEntity<?> noFacultyFoundException(NoFacultyFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Faculty Found in database").body("Faculty not present in database").build());
	}
	@ExceptionHandler(InvalidFacultyIdException.class)
	public ResponseEntity<?> invalidFacultyIdException(InvalidFacultyIdException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Faculty Found by id").body("Faculty not present  by id indatabase").build());
	
	}

}
