package com.diaz.tennis.config;

import java.io.InputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.diaz.tennis.model.Country;
import com.diaz.tennis.model.Player;
import com.diaz.tennis.repository.CountryRepository;
import com.diaz.tennis.repository.PlayerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DataLoader implements CommandLineRunner {
	
    private PlayerRepository playerRepository;
    private CountryRepository countryRepository;
    private ObjectMapper objectMapper;
    
    public DataLoader(PlayerRepository playerRepository, CountryRepository countryRepository, ObjectMapper objectMapper) {
        this.playerRepository = playerRepository;
        this.countryRepository = countryRepository;
        this.objectMapper = objectMapper;
    }
    
    /**
     * Import data from "/dataEntry/headtohead.json" into database at launch.
     * Precision : import Country before Player due to FK constraint.
     */
    @Override
    public void run(String... args) throws Exception {
        InputStream is = getClass().getResourceAsStream("/dataEntry/headtohead.json");
        PlayerWrapper playerWrapper = objectMapper.readValue(is, new TypeReference<PlayerWrapper>() {});
        List<Player> players = playerWrapper.getPlayers();
        players.forEach(player -> {
            player.setId(null);
        });
        Set<Country> countries = players.stream()
                .map(Player::getCountry)
                .collect(Collectors.toSet());
        countryRepository.saveAll(countries);
        playerRepository.saveAll(players);
    }    

}
