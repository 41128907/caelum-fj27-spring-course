package br.com.alura.forum.dto;

public class MessageCodeOutput {
	private String message;
	private int code;
	
	public static MessageCodeOutput of(String message, int code) {
		return new MessageCodeOutput( message, code);
	}
	
	private MessageCodeOutput(String message, int code) {
		this.message = message;
		this.code = code;
	}


	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
}
