package telran.currency;

import java.util.*;
import telran.currency.service.FixerApiPerDay;
import telran.view.*;

public class CurrencyConvertorAppl {

	public static void main(String[] args) {
		FixerApiPerDay fixerApi = new FixerApiPerDay();
		List<Item> menuItems = CurrencyItems.getItems(fixerApi);
		Menu menu = new Menu("Currency converter application", menuItems.toArray(Item[]::new));
		menu.perform(new SystemInputOutput());
		
	}

}
