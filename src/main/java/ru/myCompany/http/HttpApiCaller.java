package ru.myCompany.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Timofey on 25.12.2016.
 */
@Service
public class HttpApiCaller {

    public <T> T sendGet(URL url, Map<String, String> header, Class<T> returnType) {
        try {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            for (Map.Entry<String, String> headerEntry : header.entrySet()) {
                connection.setRequestProperty(headerEntry.getKey(), headerEntry.getValue());
            }
            if(connection.getResponseCode()!=200)
                throw new RuntimeException("Response code is not 200. Response code is " + connection.getResponseCode());
            String responseBody = getResponseBody(connection);
            return convertFromJson(responseBody, returnType);
        } catch (Exception e) {
            StringBuilder errorText = new StringBuilder();
            errorText.append("An error occurred while calling http method.\n");
            errorText.append("Url=[").append(url).append("]\n");
            for (Map.Entry<String, String> headerEntry : header.entrySet()) {
                errorText.append("Header ")
                        .append(headerEntry.getKey())
                        .append("=[")
                        .append(headerEntry.getValue())
                        .append("]\n");
            }
            throw new RuntimeException(errorText.toString(), e);
        }
    }

    private String getResponseBody(HttpURLConnection connection) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to get response body. ", e);
        }
    }

    private <T> T convertFromJson(String json, Class<T> type) {
        try {
            return new ObjectMapper().readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to convert JSON " +
                    "to instance of " + type + " class. JSON: " + json + ". ", e);
        }
    }
}
