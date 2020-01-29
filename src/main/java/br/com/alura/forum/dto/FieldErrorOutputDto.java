package br.com.alura.forum.dto;

import org.springframework.validation.FieldError;

public class FieldErrorOutputDto {
	
	private String field;
	private String message;

	public FieldErrorOutputDto(String field, String defaultMessage) {
		this.field = field;  
		this.message = defaultMessage;
	}
	
	public FieldErrorOutputDto(FieldError error) {
		this.field = error.getField();  
		this.message = error.getDefaultMessage();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

}
