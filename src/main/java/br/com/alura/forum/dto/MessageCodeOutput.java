package br.com.alura.forum.dto;

import java.util.List;

public class MessageCodeOutput {
	private String message;
	private int code;
	private List<FieldErrorOutputDto> fieldErrors;

	public static MessageCodeOutput of(String message, int code) {
		return of(message, code, null);
	}

	public static MessageCodeOutput of(String message, int code, List<FieldErrorOutputDto> fieldErrors) {
		return new MessageCodeOutput(message, code, fieldErrors);
	}

	private MessageCodeOutput(String message, int code, List<FieldErrorOutputDto> fieldErrors) {
		this.message = message;
		this.code = code;
		this.fieldErrors = fieldErrors;
	}

	public String getMessage() {
		return message;
	}

	public int getCode() {
		return code;
	}

	public List<FieldErrorOutputDto> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorOutputDto> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
}
