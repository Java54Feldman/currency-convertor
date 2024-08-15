package telran.currency.service;

import java.util.*;

public class FixerApiPerDay extends AbstractCurrencyConvertor {
	protected String uriString = "https://data.fixer.io/api/latest?access_key=81ebf276e1ed808b58591b5fb05c34eb";
	//TODO additional encapsulation fields
	public FixerApiPerDay() {
		rates = getRates();
	}
	private HashMap<String, Double> getRates() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String> strongestCurrencies(int amount) {
		refresh();
		return super.strongestCurrencies(amount);
	}
	@Override
	public List<String> weakestCurrencies(int amount) {
		refresh();
		return super.weakestCurrencies(amount);
	}
	@Override
	public double convert(String codeFrom, String codeTo, int amount) {
		refresh();
		return super.convert(codeFrom, codeTo, amount);
	}
	private void refresh() {
		// TODO Auto-generated method stub
		//checks whether refresh is needed
		//if so it calls getRates method for updating "rates" map
		//one per day
		//есть время последнего обращения, сравнивается (LocalDate)
		
	}
}
