package ru.maltseva.elena;

import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.factory.ObjectMapperFactory;
import ru.maltseva.elena.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import ru.maltseva.elena.service.PokemonFetchingService;
import ru.maltseva.elena.service.PokemonFethingServiceImpl;
import ru.maltseva.elena.service.PokemonFightingClubService;
import ru.maltseva.elena.service.PokemonFightingClubServiceImpl;
import ru.maltseva.elena.wwww.MainWindows;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Http {
    public static void main(String[] args) throws IOException {
        PokemonFetchingService pokemonFethingService = new PokemonFethingServiceImpl();

        Pokemon ditto = pokemonFethingService.fetchByName("ditto");
        System.out.println(ditto);

        Pokemon squirtle = pokemonFethingService.fetchByName("squirtle");
        System.out.println(squirtle);

        PokemonFightingClubService pokemonFightingClubService = new PokemonFightingClubServiceImpl();
        System.out.println(pokemonFightingClubService.doBattle(squirtle, squirtle));

        downloadFiles(squirtle.getPicture(), "C:\\ForJava\\HTTP-JSON-Wiremock\\src\\main\\resources\\winner.jpg");

        MainWindows mw = new MainWindows();

    }

    public static void downloadFiles(String strURL, String strPath) throws IOException {
        URL connection = new URL(strURL);
        HttpURLConnection urlconn;
        urlconn = (HttpURLConnection) connection.openConnection();
        urlconn.setRequestMethod("GET");
        urlconn.connect();
        InputStream in = null;
        in = urlconn.getInputStream();

        OutputStream writer = new FileOutputStream(strPath);
        byte[] buffer = new byte[1];
        int c = in.read(buffer);
        while (c > 0) {
            writer.write(buffer, 0, c);
            c = in.read(buffer);
            //System.out.print(c + " ");
        }
        writer.flush();
        writer.close();
        in.close();
    }




}
