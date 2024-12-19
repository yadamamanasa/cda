package org.jsp.cda.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvalidIdException  extends RuntimeException{
	
	private String message;
	 @Override
	public String getMessage() {
		return this.message;
	}
	

}
