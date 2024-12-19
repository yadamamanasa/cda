package org.jsp.cda.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoCourseFoundException  extends RuntimeException{
	
	private String message;
	 public NoCourseFoundException(String message) {
		 this.message = message;
	 }
	 @Override
	public String getMessage() {
		
		return this.message;
	}
	 
	

}
