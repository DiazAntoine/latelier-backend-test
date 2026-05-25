package com.diaz.tennis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diaz.tennis.model.Player;

/**
 * Pour le relecteur : il a ici été choisi de passer uniquement par JPARepository et non Hibernate
 * car les requêtes/calculs demandés peuvent être réalisé directement sur les données en BDD
 * pour éviter de charger des données inutilement
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	
	Optional<Player> findById(Long id);
	
	/**
	 * Native query that calculate the median height of players
	 * 
	 * @return median height of players
	 */
	@Query(value = "SELECT PERCENTILE_CONT(0.5) WITHIN GROUP (ORDER BY height) AS median_height "
			+ "FROM Player "
			+ "WHERE height IS NOT NULL"
			, nativeQuery = true)
	Optional<Double> calculatePlayersMedianHeight();
	
	/**
	 * Native query that calculate the average BMI of the players
	 * 
	 * @return average BMI of the players
	 */
	@Query(value = "SELECT AVG( (weight / 1000.0) / POWER(height / 100.0, 2) ) AS avg_bmi "
			+ "FROM Player "
			+ "WHERE height IS NOT NULL "
			+ "AND weight IS NOT NULL"
			, nativeQuery = true)
	Optional<Double> calculatePlayersAverageBMI();
    
    /**
	 * Native query that calculate the country with the highest win rate
	 * 
	 * @return code of the country with the highest win rate
	 */
	@Query(value = "SELECT c.code "
			+ "FROM Country c "
			+ "JOIN Player p ON p.country_code = c.code "
			+ "JOIN Player_Last pl ON pl.player_id = p.id "
			+ "GROUP BY c.code "
			+ "ORDER BY CASE WHEN COUNT(pl.last) = 0 "
			+ "THEN 0 "
			+ "ELSE COUNT(pl.last) FILTER (WHERE pl.last = 1) * 1.0 / COUNT(pl.last) END DESC "
			+ "LIMIT 1"
			, nativeQuery = true)
	Optional<String> calculateHighestWinRateCountry();
	
}
