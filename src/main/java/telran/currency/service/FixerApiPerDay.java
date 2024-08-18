package telran.currency.service;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.*;
import java.time.*;
import java.util.*;

import org.json.JSONObject;

public class FixerApiPerDay extends AbstractCurrencyConvertor implements FixerApiConfigurations {
	private static long lastRequestTimestamp;

	public FixerApiPerDay() {
		refreshIfNeeded();
	}
	private void getRates() {
		try {
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpRequest	request = HttpRequest.newBuilder(new URI(uriString)).build();
			HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
			if (response.statusCode() != 200) {
			    throw new RuntimeException("API request failed with status code: " + response.statusCode());
			}
			JSONObject jsonObject = new JSONObject(response.body());
			fillRatesAndTimeStamp(jsonObject);
			saveRates(jsonObject);
		} catch (Exception e) {
			throw new RuntimeException("Failed to get rates: " + e.getMessage());
		} 
	}
	private void fillRatesAndTimeStamp(JSONObject jsonObject) {
		JSONObject jsonRates = jsonObject.getJSONObject(RATES);
		HashMap<String, Double> newRates = new HashMap<String, Double>();
		jsonRates.keySet().forEach(key -> newRates.put(key, jsonRates.getDouble(key)));

		rates = newRates;
		lastRequestTimestamp = jsonObject.getLong(TIMESTAMP);
	}
	private void saveRates(JSONObject jsonObject) {
		try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
			writer.println(jsonObject.toString());
		} catch (IOException e) {
            throw new RuntimeException("Failed to save rates", e);
        }
	}
	@Override
	public List<String> strongestCurrencies(int amount) {
		refreshIfNeeded();
		return super.strongestCurrencies(amount);
	}

	@Override
	public List<String> weakestCurrencies(int amount) {
		refreshIfNeeded();
		return super.weakestCurrencies(amount);
	}
	@Override
	public double convert(String codeFrom, String codeTo, int amount) {
		refreshIfNeeded();
		return super.convert(codeFrom, codeTo, amount);
	}
	private synchronized void refreshIfNeeded() {
		boolean fileExists = Files.exists(Path.of(FILE_NAME));
        if (rates == null) {
            if (fileExists) {
                loadRatesFromFile();
            }
            if (!fileExists || isUpdateNeeded()) {
                getRates();
            }
        } else if (isUpdateNeeded()) {
            getRates();
        }
		
	}
	private void loadRatesFromFile() {
		try (BufferedReader reader = Files.newBufferedReader(Path.of(FILE_NAME))) {
            JSONObject jsonObject = new JSONObject(reader.readLine());
            fillRatesAndTimeStamp(jsonObject);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load rates from file", e);
        }
		
	}
	private boolean isUpdateNeeded() {
        long currentTime = Instant.now().getEpochSecond();
        return (currentTime - lastRequestTimestamp) >= UPDATE_INTERVAL_SECONDS;
	}
	
}
