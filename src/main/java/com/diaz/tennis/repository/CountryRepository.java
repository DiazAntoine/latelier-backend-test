package com.diaz.tennis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diaz.tennis.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
