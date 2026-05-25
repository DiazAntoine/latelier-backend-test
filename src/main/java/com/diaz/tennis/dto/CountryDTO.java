package com.diaz.tennis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CountryDTO {

	@NotBlank(message = "Code is required")
	@Pattern(regexp = "^[a-zA-Z]{3}$", message = "Short name must be exactly 3 letters")
	private String code;

	@Pattern(
			regexp = "^(https?://).+\\..+",
			message = "Picture URL is invalid"
	)
	private String picture;
	
	public CountryDTO() {
		
	}

	public CountryDTO(String code, String picture) {
		this.code = code;
		this.picture = picture;
	}
    
    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
