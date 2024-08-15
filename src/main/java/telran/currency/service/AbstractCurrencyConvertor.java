package telran.currency.service;

import java.util.*;

public abstract class AbstractCurrencyConvertor implements CurrencyConvertor {
	protected Map<String, Double> rates; //key - currency ISO code
	//value - amount of code's units in 1 EUR
	@Override
	public List<String> strongestCurrencies(int amount) {
		// TODO Auto-generated method stub
		// streams
		//amount - number of currencies
		return null;
	}

	@Override
	public List<String> weakestCurrencies(int amount) {
		// TODO Auto-generated method stub
		// streams
		return null;
	}

	@Override
	public double convert(String codeFrom, String codeTo, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

}
