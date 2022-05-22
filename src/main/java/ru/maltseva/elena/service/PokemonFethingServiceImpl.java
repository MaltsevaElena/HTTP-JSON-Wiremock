package ru.maltseva.elena.service;

import ru.maltseva.elena.entity.Pokemon;

public class PokemonFethingServiceImpl implements PokemonFetchingService {
    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        return null;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        return new byte[0];
    }
}
