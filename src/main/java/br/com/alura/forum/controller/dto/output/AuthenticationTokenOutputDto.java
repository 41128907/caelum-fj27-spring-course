package br.com.alura.forum.controller.dto.output;

public class AuthenticationTokenOutputDto {
	
	public String tokkenType;
	public String token;
	
	public AuthenticationTokenOutputDto(String tokkenType, String token) {
		this.tokkenType = tokkenType;
		this.token = token;
	}
	public String getTokkenType() {
		return tokkenType;
	}
	public void setTokkenType(String tokkenType) {
		this.tokkenType = tokkenType;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
