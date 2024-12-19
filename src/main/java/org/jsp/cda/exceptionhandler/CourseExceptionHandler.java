package org.jsp.cda.exceptionhandler;

import org.jsp.cda.exceptionclasses.NoCourseFoundException;
import org.jsp.cda.reponsestructure.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CourseExceptionHandler {
	
	@ExceptionHandler(NoCourseFoundException.class)
	public ResponseEntity<?> noDepartmentFoundException(NoCourseFoundException e){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseStructure.builder().httpStatus(HttpStatus.NOT_FOUND.value()).message("No Course Found in database").body("Course not present in database").build());
	}

}
