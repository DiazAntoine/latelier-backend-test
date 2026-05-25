package com.diaz.tennis.repository;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.diaz.tennis.config.PlayerWrapper;
import com.diaz.tennis.model.Country;
import com.diaz.tennis.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@DataJpaTest
public class PlayerRepositoryTest {
	
	private static final String NOVAK = "Novak";
	private static final String SRB = "SRB";
	private static final Double MEDIAN_HEIGH = 185.0;
	private static final Double AVERAGE_BMI = 23.357838995505833;
	
	@Autowired
    private PlayerRepository playerRepository;
	
	@Autowired
    private CountryRepository countryRepository;

    @BeforeEach
    public void init() throws Exception {
    	InputStream is = getClass().getResourceAsStream("/dataEntry/headtohead.json");
        PlayerWrapper playerWrapper = new ObjectMapper().readValue(is, new TypeReference<PlayerWrapper>() {});
        List<Player> players = playerWrapper.getPlayers();
        Set<Country> countries = players.stream()
                .map(Player::getCountry)
                .collect(Collectors.toSet());
        countryRepository.saveAll(countries);
        playerRepository.saveAll(players);
    }
    
    @Test
    void testFindById() {
        Optional<Player> player = playerRepository.findById(52L);
        Assertions.assertTrue(player.isPresent());
        Assertions.assertEquals(NOVAK, player.get().getFirstname());
    }

    @Test
    void testCalculatePlayersMedianHeight() {
        Optional<Double> median = playerRepository.calculatePlayersMedianHeight();
        Assertions.assertTrue(median.isPresent());
        Assertions.assertEquals(median.get(), MEDIAN_HEIGH);
    }

    @Test
    void testCalculatePlayersAverageBMI() {
        Optional<Double> avgBmi = playerRepository.calculatePlayersAverageBMI();
        Assertions.assertTrue(avgBmi.isPresent());
        Assertions.assertEquals(avgBmi.get(), AVERAGE_BMI);
    }

    @Test
    void testCalculateHighestWinRateCountry() {
        Optional<String> countryCode = playerRepository.calculateHighestWinRateCountry();
        Assertions.assertTrue(countryCode.isPresent());
        Assertions.assertEquals(countryCode.get(), SRB);
    }

}
