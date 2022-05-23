package ru.maltseva.elena.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.factory.ObjectMapperFactory;
import ru.maltseva.elena.factory.ObjectMapperFactoryImpl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class PokemonFethingServiceImpl implements PokemonFetchingService {
    private  String strUrl = "https://pokeapi.co/api/v2/pokemon/";
    private final String path = "C:\\ForJava\\HTTP-JSON-Wiremock\\src\\main\\resources\\winner.jpg";

    @Override
    public Pokemon fetchByName(String name) throws IllegalArgumentException {
        String json;

        ObjectMapperFactory mapperFactory;
        ObjectMapper mapper;

        Pokemon pokemon = null;

        JsonNode jsonNode;
        JsonNode stats;
        JsonNode stat;
        JsonNode number;
        JsonNode sprites;

        String nameStat;

        json = httpRequest(strUrl + name);

        mapperFactory = new ObjectMapperFactoryImpl();
        mapper = mapperFactory.getObjectMapper();

        try {
            pokemon = mapper.readValue(json, Pokemon.class);
            jsonNode = mapper.readValue(json, JsonNode.class);
            stats = jsonNode.get("stats");

            for (JsonNode jnode : stats) {
                stat = jnode.findValue("stat");

                nameStat = stat.get("name").asText();

                number = jnode.get("base_stat");
                if (nameStat.equalsIgnoreCase("hp")) {
                    pokemon.setHp(number.shortValue());
                } else if (nameStat.equalsIgnoreCase("attack")) {
                    pokemon.setAttack(number.shortValue());
                } else if (nameStat.equalsIgnoreCase("defense")) {
                    pokemon.setDefense(number.shortValue());
                }
            }
            sprites = jsonNode.get("sprites");
            pokemon.setPicture(sprites.get("front_default").asText());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pokemon;
}
    public String httpRequest(String stringUrl) {
        URL url;
        HttpURLConnection connection = null;

        int responseCode;

        InputStream in;
        String result = " ";

        try {
            url = new URL(stringUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);

            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome/101.0.4951.67");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();

            responseCode = connection.getResponseCode();
            System.out.println("GET Response Code : " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success

                in = new BufferedInputStream(connection.getInputStream());
                result = IOUtils.toString(in);

                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        } catch (MalformedURLException | ProtocolException e) {
            e.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            assert connection != null;
            connection.disconnect();
        }
        return result;
    }

    @Override
    public byte[] getPokemonImage(String name) throws IllegalArgumentException {
        return new byte[1];
    }
}
