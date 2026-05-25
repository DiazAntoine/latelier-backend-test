package com.diaz.tennis.service;

import java.util.List;

import com.diaz.tennis.dto.PlayerRequestDTO;
import com.diaz.tennis.dto.PlayerResponseDTO;
import com.diaz.tennis.dto.PlayerStatisticsDTO;

public interface PlayerService {

	/**
	 * Find all players sorted by rank
	 * @return list of players
	 */
	List<PlayerResponseDTO> findAllPlayersSortedByRank();
	
	/**
	 * Find player by given id
	 *
	 * @param id
	 * @return player
	 */
	PlayerResponseDTO getPlayerById(Long id);
	
	/**
	 * Calculate some statistics on players
	 * @return statistics on players
	 */
	PlayerStatisticsDTO calculateStatistics();
	
	/**
	 * Create player
	 * 
	 * @param player to be created
	 * @return player created
	 */
	PlayerResponseDTO createPlayer(PlayerRequestDTO player);

}
