package com.diaz.tennis.mapper;

import java.util.List;

import com.diaz.tennis.dto.PlayerRequestDTO;
import com.diaz.tennis.dto.PlayerResponseDTO;
import org.mapstruct.Mapper;

import com.diaz.tennis.model.Player;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

	Player dtoToEntity(PlayerRequestDTO playerDTO);
	
	PlayerResponseDTO entityToDTO(Player player);
	
	List<PlayerResponseDTO> entitiesToDTOs(List<Player> players);
}
