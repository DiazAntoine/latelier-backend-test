# Tennis Application

This document describes how to configure and run the TennisApplication.
This application is a RESTful service developed with Spring Boot to manage information about tennis players.

## Prerequisites

- Java JDK 21
- Maven 3.9 or superior

## Configuration / Running the application

1. First clone the repo : git clone https://github.com/DiazAntoine/latelier-backend-test.git
2. Compile the project : mvn clean install
3. Launch the application : mvn spring-boot:run
4. You can access it at http://localhost:8080

## Using the API

The API exposes the following endpoints:

- GET /players => get all players sorted by rank
- GET /players/{id} => get a player by ID
- GET /players/statistics => get some statistics on players : the country with the highest win ratio, the average BMI of the players and the median height of the players
- POST /players => create a player with the json structure :

    ~~~~json
    {
        "id": 22,
        "firstname": "Diaz",
        "lastname": "Antoine",
        "shortname": "A.DIAZ",
        "sex": "M",
        "country": {
            "picture": "https://tenisu.latelier.co/resources/Serbie.png",
            "code": "SRB"
        },
        "picture": "https://tenisu.latelier.co/resources/ADiaz.png",
        "data": {
            "rank": 22,
            "points": 2222,
            "weight": 76000,
            "height": 180,
            "age": 27,
            "last": [1, 0, 0, 0, 0]
        }
    }
    ~~~~

## What is missing

- Improve exception handling: fix having hardcoded message + fix returning technical exception
- Fix having long hardcoded queries in PlayerRepository
- Add @GeneratedValue(strategy = GenerationType.IDENTITY) to @Id : problem with DataLoader that manually set the id
- Having separate data between the PlayerRepositoryTest TUs
- Add tests for GlobalExceptionHandler
- Adding Logs
