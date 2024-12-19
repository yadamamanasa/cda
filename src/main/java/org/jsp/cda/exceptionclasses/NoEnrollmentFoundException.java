package org.jsp.cda.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoEnrollmentFoundException  extends RuntimeException{
	private String message;
	 public NoEnrollmentFoundException(String message) {
		 this.message = message;
	 }
	 @Override
	public String getMessage() {
		
		return this.message;
	}
	 
	

}
