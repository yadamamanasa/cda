package org.jsp.cda.exceptionclasses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvalidUserIdException extends RuntimeException {
	private String message;
	 @Override
	public String getMessage() {
		return this.message;
	}

}
