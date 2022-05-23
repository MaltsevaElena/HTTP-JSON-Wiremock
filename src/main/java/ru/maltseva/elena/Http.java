package ru.maltseva.elena;

import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.factory.ObjectMapperFactory;
import ru.maltseva.elena.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Http {
    public static void main(String[] args) throws IOException {
        String urlSquirtle = "https://pokeapi.co/api/v2/pokemon/squirtle";
        String squirtleJson = httpRequest(urlSquirtle);

        Pokemon squirtle = creatingPokemonFromJson(squirtleJson);
        System.out.println(squirtle);

        String urlDiglett = "https://pokeapi.co/api/v2/pokemon/diglett";
        String diglettJson = httpRequest(urlDiglett);

        Pokemon diglett = creatingPokemonFromJson(diglettJson);
        System.out.println(diglett);

    }

    public static Pokemon creatingPokemonFromJson(String json) throws JsonProcessingException {
        ObjectMapperFactory mapperFactory = new ObjectMapperFactoryImpl();
        ObjectMapper mapper = mapperFactory.getObjectMapper();

        Pokemon pokemon = mapper.readValue(json, Pokemon.class);


        JsonNode jsonNode = mapper.readValue(json, JsonNode.class);
        JsonNode stats = jsonNode.get("stats");
        for (JsonNode jnode : stats) {
            JsonNode stat = jnode.findValue("stat");

            String name = stat.get("name").asText();

            JsonNode number = jnode.get("base_stat");
            if (name.equalsIgnoreCase("hp")) {
                pokemon.setHp(number.shortValue());
            } else if (name.equalsIgnoreCase("attack")) {
                pokemon.setAttack(number.shortValue());
            } else if (name.equalsIgnoreCase("defense")) {
                pokemon.setDefense(number.shortValue());
            }

        }
        return pokemon;
    }
    public static String httpRequest(String stringUrl) {
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
}
