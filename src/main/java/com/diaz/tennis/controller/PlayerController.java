package com.diaz.tennis.controller;

import java.util.List;

import com.diaz.tennis.dto.PlayerRequestDTO;
import com.diaz.tennis.dto.PlayerResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diaz.tennis.dto.PlayerStatisticsDTO;
import com.diaz.tennis.service.PlayerService;

@RestController
@RequestMapping(value = "/players",
	produces = "application/json")
@Tag(name = "Players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }
    
    @GetMapping
    @Operation(summary = "Get all players", description = "Returns the list of all players sorted by rank")
    @ApiResponse(responseCode = "200", description = "Players list successfully returned")
    public List<PlayerResponseDTO> findAllPlayersSortedByRank() {
    	return playerService.findAllPlayersSortedByRank();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get a player by ID", description = "Returns a player matching the provided ID")
    @ApiResponse(responseCode = "200", description = "Player found")
    @ApiResponse(responseCode = "404", description = "Player not found")
    public PlayerResponseDTO getPlayerById(
            @Parameter(description = "Player ID", required = true, example = "1")
            @PathVariable Long id) {
    	return playerService.getPlayerById(id);
	}
    
    @GetMapping("/statistics")
    @Operation(summary = "Get Statistics on players", description = "Returns statistics for all players")
    @ApiResponse(responseCode = "200", description = "Statistics successfully calculated")
    public PlayerStatisticsDTO calculateStatistics() {
		return playerService.calculateStatistics();
	}
	
    @PostMapping(consumes = "application/json")
    @Operation(summary = "Create a player", description = "Creates a new player and persists it in the databas")
    @ApiResponse(responseCode = "201", description = "Player successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid request body")
    public ResponseEntity<PlayerResponseDTO> createPlayer(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Player data to create", required = true)
            @RequestBody @Valid PlayerRequestDTO player) {
        PlayerResponseDTO playerSaved = playerService.createPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerSaved);
    }

}