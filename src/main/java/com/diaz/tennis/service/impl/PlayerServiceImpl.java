package com.diaz.tennis.service.impl;

import java.util.List;

import com.diaz.tennis.dto.PlayerRequestDTO;
import com.diaz.tennis.dto.PlayerResponseDTO;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.diaz.tennis.dto.PlayerStatisticsDTO;
import com.diaz.tennis.mapper.PlayerMapper;
import com.diaz.tennis.model.Player;
import com.diaz.tennis.repository.PlayerRepository;
import com.diaz.tennis.service.PlayerService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PlayerServiceImpl implements PlayerService {
	
	private PlayerRepository playerRepository;
	
	private PlayerMapper playerMapper;
	
	public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper) {
	    this.playerRepository = playerRepository;
	    this.playerMapper = playerMapper;
	}
	
	public List<PlayerResponseDTO> findAllPlayersSortedByRank() {
		List<Player> players = playerRepository.findAll(Sort.by(Sort.Direction.ASC, "data.rank"));
	    return playerMapper.entitiesToDTOs(players);
	}
	
	public PlayerResponseDTO getPlayerById(Long id) {
	    Player player = playerRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Player not found with id " + id));
	    return playerMapper.entityToDTO(player);
	}
	
	public PlayerStatisticsDTO calculateStatistics() {
		PlayerStatisticsDTO statistics = new PlayerStatisticsDTO();
		
		statistics.setHighestWinRateCountry(
				playerRepository.calculateHighestWinRateCountry().orElse("N/A"));
		
		statistics.setPlayersMedianHeight(
				playerRepository.calculatePlayersMedianHeight().orElse(0.0));
		
		statistics.setPlayersAverageBMI(
				playerRepository.calculatePlayersAverageBMI().orElse(0.0));
		
		return statistics;
	}
	
	public PlayerResponseDTO createPlayer(PlayerRequestDTO player) {
		if (player == null) {
	        throw new IllegalArgumentException("PlayerDTO is null");
	    }

		Player playerSaved = playerMapper.dtoToEntity(player);
		return playerMapper.entityToDTO(playerRepository.save(playerSaved));
	}

}
