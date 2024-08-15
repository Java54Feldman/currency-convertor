package telran.currency;

import java.util.List;

import telran.currency.service.CurrencyConvertor;
import telran.view.Item;

public class CurrencyItems {
	private static CurrencyConvertor currencyConvertor;
	public static List<Item> getItems(CurrencyConvertor currencyConvertor) {
		CurrencyItems.currencyConvertor = currencyConvertor;
		//TODO
		return null;
	}
}
