package ru.maltseva.elena;

import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.service.PokemonFetchingService;
import ru.maltseva.elena.service.PokemonFethingServiceImpl;
import ru.maltseva.elena.service.PokemonFightingClubService;
import ru.maltseva.elena.service.PokemonFightingClubServiceImpl;

import java.io.*;


public class Main {
    public static void main(String[] args) throws IOException {
        PokemonFetchingService pokemonFethingService;
        PokemonFightingClubService pokemonFightingClubService;

        Pokemon ditto;
        Pokemon squirtle;
        Pokemon winner;


        pokemonFethingService = new PokemonFethingServiceImpl();

        ditto = pokemonFethingService.fetchByName("ditto");
        squirtle = pokemonFethingService.fetchByName("squirtle");

        pokemonFightingClubService = new PokemonFightingClubServiceImpl();

        winner = pokemonFightingClubService.doBattle(squirtle, ditto);

        pokemonFethingService.getPokemonImage(winner.getPokemonName());

        pokemonFightingClubService.showWinner(winner);

    }

}
