package com.diaz.tennis.mapper;

import org.mapstruct.Mapper;

import com.diaz.tennis.dto.CountryDTO;
import com.diaz.tennis.model.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {
	
	Country dtoToEntity (CountryDTO countryDTO);
	
	CountryDTO entityToDTO (Country countryDTO);

}
