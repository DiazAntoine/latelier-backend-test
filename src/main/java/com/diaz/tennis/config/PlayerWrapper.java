package com.diaz.tennis.config;

import java.util.List;

import com.diaz.tennis.model.Player;

public class PlayerWrapper {

	private List<Player> players;

	public PlayerWrapper() {
	}
	
	public PlayerWrapper(List<Player> players) {
		this.players = players;
	}
	
	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
}
