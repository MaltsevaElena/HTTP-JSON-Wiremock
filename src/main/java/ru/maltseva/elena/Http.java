package ru.maltseva.elena;

import ru.maltseva.elena.entity.Pokemon;
import ru.maltseva.elena.factory.ObjectMapperFactory;
import ru.maltseva.elena.factory.ObjectMapperFactoryImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class Http {
    public static void main(String[] args) throws IOException {
        String str = "https://pokeapi.co/api/v2/pokemon/squirtle";
        String ditto = httpRequest(str);


    }

    public static String httpRequest(String stringUrl) {
        URL url;
        HttpURLConnection connection = null;

        int responseCode;

        InputStream in;
        String result = " ";

        JSONObject json = null;

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
                json = new JSONObject(result);
                // System.out.println(json);
                in.close();
            } else {
                System.out.println("GET request not worked");
            }
        } catch (MalformedURLException | JSONException | ProtocolException e) {
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
