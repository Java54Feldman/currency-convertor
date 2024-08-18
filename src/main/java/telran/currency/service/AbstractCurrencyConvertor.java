package telran.currency.service;

import java.util.*;

public abstract class AbstractCurrencyConvertor implements CurrencyConvertor {
	protected Map<String, Double> rates; 
	//key - currency ISO code
	//value - amount of code's units in 1 EUR
	@Override
	public List<String> strongestCurrencies(int amount) {
		return rates.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.limit(amount)
				.map(entry -> entry.getKey() + " : " + entry.getValue())
				.toList();
	}

	@Override
	public List<String> weakestCurrencies(int amount) {
		return rates.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(amount)
				.map(entry -> entry.getKey() + " : " + entry.getValue())
				.toList();
	}

	@Override
	public double convert(String codeFrom, String codeTo, int amount) {
		return rates.get(codeTo) / rates.get(codeFrom) * amount;
	}
	@Override
	public HashSet<String> getAllCodes() {
		return new HashSet<String>(rates.keySet());
	}
	@Override
	public int getMaxNumberOfCurrencies() {
		return rates.size();
	}

}
