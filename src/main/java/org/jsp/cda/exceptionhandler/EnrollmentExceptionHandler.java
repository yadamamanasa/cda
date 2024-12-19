package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.InvalidEnrollmentIdException;
import org.jsp.cda.exceptionclasses.NoEnrollmentFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnrollmentExceptionHandler {
	
	@ExceptionHandler(NoEnrollmentFoundException.class)
	public ResponseEntity<?> noFacultyFoundException(NoEnrollmentFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Enrollment Found in database").body("Enrollments not present in database").build());
	}
	@ExceptionHandler(InvalidEnrollmentIdException.class)
	public ResponseEntity<?> invalidEnrollmentIdException(InvalidEnrollmentIdException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Enrollment Found in database").body("Enrollments not present in database").build());
	}

}
