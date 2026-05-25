package com.diaz.tennis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.diaz.tennis.dto.PlayerDataDTO;
import com.diaz.tennis.dto.PlayerStatisticsDTO;
import com.diaz.tennis.mapper.PlayerMapper;
import com.diaz.tennis.model.Player;
import com.diaz.tennis.model.PlayerData;
import com.diaz.tennis.repository.PlayerRepository;
import com.diaz.tennis.service.impl.PlayerServiceImpl;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class PlayerServiceTest {

	private static final String SUI = "SUI";
	private static final Double DOUBLE_VALUE = 1.0;
	
	@Mock
    private PlayerRepository playerRepository;

    @Mock
    private PlayerMapper playerMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;
    
    @Test
    void testFindAllPlayersSortedByRank() {
    	Player player1 = new Player();
    	player1.setId(52L);
    	PlayerData data1 = new PlayerData();
    	data1.setRank(2);
    	player1.setData(data1);

    	Player player2 = new Player();
    	player2.setId(17L);
    	PlayerData data2 = new PlayerData();
    	data2.setRank(1);
    	player2.setData(data2);
    	
    	PlayerDTO dto1 = new PlayerDTO();
    	dto1.setId(52L);
    	PlayerDataDTO d1 = new PlayerDataDTO();
    	d1.setRank(2);
    	dto1.setData(d1);

    	PlayerDTO dto2 = new PlayerDTO();
    	dto2.setId(17L);
    	PlayerDataDTO d2 = new PlayerDataDTO();
    	d2.setRank(1);
    	dto2.setData(d2);

    	List<Player> players = Arrays.asList(player2, player1); 
        List<PlayerDTO> dtoList = Arrays.asList(dto2, dto1);

        when(playerRepository.findAll(any(Sort.class))).thenReturn(players);
        when(playerMapper.entitiesToDTOs(players)).thenReturn(dtoList);

        List<PlayerDTO> result = playerService.findAllPlayersSortedByRank();

        assertEquals(dtoList, result);
        verify(playerRepository).findAll(any(Sort.class));
        verify(playerMapper).entitiesToDTOs(players);
    }
    
    @Test
    void testGetPlayerByIdSuccess() {
        Player player = new Player();
        PlayerDTO dto = new PlayerDTO();

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));
        when(playerMapper.entityToDTO(player)).thenReturn(dto);

        PlayerDTO result = playerService.getPlayerById(1L);

        assertEquals(dto, result);
    }

    @Test
    void testGetPlayerByIdNotFound() {
        when(playerRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> playerService.getPlayerById(1L));
    }
    
    @Test
    void testCalculateStatistics() {
        when(playerRepository.calculateHighestWinRateCountry()).thenReturn(Optional.of(SUI));
        when(playerRepository.calculatePlayersMedianHeight()).thenReturn(Optional.of(DOUBLE_VALUE));
        when(playerRepository.calculatePlayersAverageBMI()).thenReturn(Optional.of(DOUBLE_VALUE));

        PlayerStatisticsDTO result = playerService.calculateStatistics();

        assertEquals(SUI, result.getHighestWinRateCountry());
        assertEquals(DOUBLE_VALUE, result.getPlayersMedianHeight());
        assertEquals(DOUBLE_VALUE, result.getPlayersAverageBMI());
    }
    
    @Test
    void testCalculateStatisticsEmptyValues() {
        when(playerRepository.calculateHighestWinRateCountry()).thenReturn(Optional.empty());
        when(playerRepository.calculatePlayersMedianHeight()).thenReturn(Optional.empty());
        when(playerRepository.calculatePlayersAverageBMI()).thenReturn(Optional.empty());

        PlayerStatisticsDTO result = playerService.calculateStatistics();

        assertEquals("N/A", result.getHighestWinRateCountry());
        assertEquals(0.0, result.getPlayersMedianHeight());
        assertEquals(0.0, result.getPlayersAverageBMI());
    }
    
    @Test
    void testCreatePlayer() {
    	PlayerDTO player = new PlayerDTO();
    	player.setId(52L);
        Player playerEntity = new Player();
    	player.setId(52L);

        when(playerMapper.dtoToEntity(player)).thenReturn(playerEntity);
        when(playerRepository.save(playerEntity)).thenReturn(playerEntity);
        when(playerMapper.entityToDTO(playerEntity)).thenReturn(player);

        PlayerDTO result = playerService.createPlayer(player);

        assertEquals(player, result);
    }

    @Test
    void testCreatePlayerNullThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> playerService.createPlayer(null));
    }
    
}
