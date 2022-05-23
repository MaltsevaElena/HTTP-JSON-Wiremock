package ru.maltseva.elena.service;

import ru.maltseva.elena.entity.Pokemon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Сервис по загрузке данных покемонов из внешнего сервиса
 */
public interface PokemonFetchingService {

    /**
     * @param name - имя покемона
     * @return сущность Pokemon
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    Pokemon fetchByName(String name) throws IllegalArgumentException;

    /**
     * @param name - имя покемона
     * @return картинка покемона в виде массива байтов
     * @throws IllegalArgumentException при условии, если имя покемона указано неверно
     */
    byte[] getPokemonImage(String name) throws IllegalArgumentException;
}
