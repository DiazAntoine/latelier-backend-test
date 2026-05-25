package com.diaz.tennis.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PlayerRequestDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    private String firstname;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name cannot exceed 50 characters")
    private String lastname;

    @NotBlank(message = "Short name is required")
    @Size(max = 10, message = "Short name cannot exceed 10 characters")
    private String shortname;

    @NotBlank(message = "Sex is required")
    @Pattern(regexp = "^[MF]$", message = "Sex must be 'M' or 'F'")
    private String sex;

    @Pattern(
            regexp = "^(https?://).+\\..+",
            message = "Picture URL is invalid"
    )
    private String picture;

    @NotNull(message = "Country is required")
    @Valid
    private CountryDTO country;

    @Valid
    private PlayerDataDTO data;

    public PlayerRequestDTO() {

    }

    public PlayerRequestDTO(Long id, String firstname, String lastname, String shortname, String sex, String picture,
                     CountryDTO country, PlayerDataDTO data) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.shortname = shortname;
        this.sex = sex;
        this.picture = picture;
        this.country = country;
        this.data = data;
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

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public PlayerDataDTO getData() {
        return data;
    }

    public void setData(PlayerDataDTO data) {
        this.data = data;
    }
}