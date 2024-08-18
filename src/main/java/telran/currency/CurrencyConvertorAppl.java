package telran.currency;

import telran.currency.service.FixerApiPerDay;
import telran.view.*;

public class CurrencyConvertorAppl {

	public static void main(String[] args) {
		FixerApiPerDay fixerApi = new FixerApiPerDay();
		Item[] menuItems = CurrencyItems.getItems(fixerApi);
		Menu menu = new Menu("Currency converter application", menuItems);
		menu.perform(new SystemInputOutput());
		
	}

}
