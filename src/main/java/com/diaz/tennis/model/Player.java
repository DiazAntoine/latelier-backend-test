package com.diaz.tennis.model;

import jakarta.persistence.*;

@Entity
public class Player {

	@Id
    @GeneratedValue
    private Long id;
    
    private String firstname;
    
    private String lastname;
    
    private String shortname;
    
    private String sex;
    
    private String picture;
    
    @ManyToOne
    private Country country;
    
    @Embedded
    private PlayerData data;

    public Player() {
    }
   
    public Player(Long id, String firstname, String lastname, String shortname, String sex, String picture,
			Country country, PlayerData data) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.shortname = shortname;
		this.sex = sex;
		this.picture = picture;
		this.country = country;
		this.data = data;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public PlayerData getData() {
        return data;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }
}