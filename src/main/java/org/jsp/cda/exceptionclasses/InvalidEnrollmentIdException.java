package org.jsp.cda.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidEnrollmentIdException extends RuntimeException {
	private String message;
	 public String getMessage() {
		return this.message;
	}
	

}
