package org.jsp.cda.exceptionclasses;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
public class NoDepartmentFoundException  extends RuntimeException{
	
	private String message;
	 public NoDepartmentFoundException(String message) {
		 this.message = message;
	 }
	 @Override
	public String getMessage() {
		
		return this.message;
	}
	 
	

}
