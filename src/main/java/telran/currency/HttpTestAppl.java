package telran.currency;

import java.net.URI;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;
import java.util.stream.Collectors;

import org.json.JSONObject;

public class HttpTestAppl {
	public static void main(String[] args) throws Exception{
		HttpClient httpClient = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(
			new URI("https://data.fixer.io/api/latest?access_key=80e4b2df2b69e4d9dbc0894827c31186"))
			.build();
		HttpResponse<String> response = 
				httpClient.send(request, BodyHandlers.ofString());
		JSONObject jsonObject = new JSONObject(response.body());
		JSONObject jsonRates = jsonObject.getJSONObject("rates");
		String[] codes = {"USD", "EUR", "ILS", "RUB", "KUK"};
		Map<String, Double> map = Arrays.stream(codes)
				.collect(Collectors
						.toMap(c -> c, c -> jsonRates.optDouble(c)));
		System.out.println(map);
	}

}
