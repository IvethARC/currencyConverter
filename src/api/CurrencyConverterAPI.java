package api;

import com.google.gson.Gson;
import model.Currency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverterAPI {

    public Currency apiCurrencyConverter(String baseCurrency, String convertedCurrency, String currencyAmount){

        URI baseUri= URI.create(
                        "https://v6.exchangerate-api.com/v6/1858575ac97a42bea12d891b/pair/"+
                        baseCurrency+"/"+convertedCurrency+"/"+currencyAmount);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(baseUri)
                .build();

        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), Currency.class);

    }

}
