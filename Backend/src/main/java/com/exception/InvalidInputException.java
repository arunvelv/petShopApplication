package com.exception;
 
public class InvalidInputException extends RuntimeException{
	
	private String code;
	private String message;
	
	public InvalidInputException(String message) {
		super(message);
		this.code=code;
		this.message=message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
 