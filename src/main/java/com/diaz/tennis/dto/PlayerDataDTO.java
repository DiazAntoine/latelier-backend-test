package com.diaz.tennis.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PlayerDataDTO {

    @NotNull(message = "Rank is required")
    @Min(value = 1, message = "Rank must be at least 1")
    @Max(value = 1000, message = "Rank must be less than 1000")
    private Integer rank;

    @NotNull(message = "Points is required")
    @Min(value = 0, message = "Points must be positive")
    private Integer points;

    @NotNull(message = "Weight is required")
    @Min(value = 0, message = "Weight must be positive")
    @Max(value = 200000, message = "Weight must be less than 200000")
    private Integer weight;

    @NotNull(message = "Height is required")
    @Min(value = 0, message = "Height must be positive")
    @Max(value = 250, message = "Height must be less than 250")
    private Integer height;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be positive")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;

    @NotNull(message = "Last results are required")
    @Size(min = 1, message = "Last results must contain at least one entry")
    private List<Integer> last;
    
    public PlayerDataDTO() {
    	
    }
	
    public PlayerDataDTO(Integer rank, Integer points, Integer weight, Integer height, Integer age, List<Integer> last) {
		this.rank = rank;
		this.points = points;
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.last = last;
	}

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Integer> getLast() {
        return last;
    }

    public void setLast(List<Integer> last) {
        this.last = last;
    }
}