package Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oracle.exchangerate.models.ExchangeRateApiResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConectionApi {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    public ExchangeRateApiResponse createConsulChangeRateTotAPI(String baseCode, String targetCode, double amount) {
        try {
            // Construye la URL de la API con el código base
            String urlStr = API_URL + baseCode;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");


            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Usa Gson para analizar la respuesta JSON
                Gson gson = new Gson();
                JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

                // Obtiene la tasa de cambio para el código objetivo
                JsonElement rates = jsonResponse.getAsJsonObject("rates").get(targetCode);
                double exchangeRate = rates.getAsDouble();

                // Calcula el resultado de la conversión
                double conversionResult = amount * exchangeRate;

                // Crea y devuelve la respuesta de la API
                return new ExchangeRateApiResponse(baseCode, targetCode, exchangeRate, amount, conversionResult);
            } else {
                System.out.println("GET request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
