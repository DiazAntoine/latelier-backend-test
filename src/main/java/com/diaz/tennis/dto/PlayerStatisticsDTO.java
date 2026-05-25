package com.diaz.tennis.dto;

public class PlayerStatisticsDTO {

	public String highestWinRateCountry;
	public Double playersAverageBMI;
	public Double playersMedianHeight;
	
	public PlayerStatisticsDTO() {
		
	}
	
	public PlayerStatisticsDTO(String highestWinRateCountry, Double playersAverageBMI, Double playersMedianHeight) {
		this.highestWinRateCountry = highestWinRateCountry;
		this.playersAverageBMI = playersAverageBMI;
		this.playersMedianHeight = playersMedianHeight;
	}
	
	public String getHighestWinRateCountry() {
		return highestWinRateCountry;
	}
	
	public void setHighestWinRateCountry(String highestWinRateCountry) {
		this.highestWinRateCountry = highestWinRateCountry;
	}
	
	public Double getPlayersAverageBMI() {
		return playersAverageBMI;
	}
	
	public void setPlayersAverageBMI(Double playersAverageBMI) {
		this.playersAverageBMI = playersAverageBMI;
	}
	
	public Double getPlayersMedianHeight() {
		return playersMedianHeight;
	}
	
	public void setPlayersMedianHeight(Double playersMedianHeight) {
		this.playersMedianHeight = playersMedianHeight;
	}

}
