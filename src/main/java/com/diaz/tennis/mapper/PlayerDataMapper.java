package com.diaz.tennis.mapper;

import org.mapstruct.Mapper;

import com.diaz.tennis.dto.PlayerDataDTO;
import com.diaz.tennis.model.PlayerData;

@Mapper(componentModel = "spring")
public interface PlayerDataMapper {

	PlayerData dtoToEntity (PlayerDataDTO countryDTO);
	
	PlayerDataDTO entityToDTO (PlayerData countryDTO);
}
