package com.vivelibre.auth.dto;

public class LoginResponseDTO {

    private String token;
    private String date;

    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

    
}
