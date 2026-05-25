package com.diaz.tennis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Country {

	@Id
    private String code;
    
	private String picture;
	
	public Country() {
		
	}

	public Country(String code, String picture) {
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
