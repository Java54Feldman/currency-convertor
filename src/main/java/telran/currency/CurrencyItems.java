package telran.currency;

import java.util.*;
import telran.currency.service.CurrencyConvertor;
import telran.view.*;

public class CurrencyItems {
	private static CurrencyConvertor currencyConvertor;

	public static Item[] getItems(CurrencyConvertor currencyConvertor) {
		CurrencyItems.currencyConvertor = currencyConvertor;
		Item[] items = { Item.of("Display strongest currencies", io -> strongestCurrencies(io, true)),
				Item.of("Display weakest currencies", io -> strongestCurrencies(io, false)),
				Item.of("Convert currencies", CurrencyItems::convert),
				Item.of("Get all codes", CurrencyItems::getAllCodes),
				Item.ofExit()};
		return items;
	}
	private static void strongestCurrencies(InputOutput io, boolean strongest) {
        int amount = io.readNumberRange("Enter the number of currencies: ", "Wrong input", 1, Integer.MAX_VALUE).intValue();
        List<String> res = strongest ? currencyConvertor.strongestCurrencies(amount) : currencyConvertor.weakestCurrencies(amount);
        io.writeLine(strongest ? "Top strongest currencies: " : "Top weakest currencies: ");
        res.forEach(io::writeLine);
    }
	private static void convert(InputOutput io) {
		HashSet<String> codes = currencyConvertor.getAllCodes();
		String codeFrom = io.readStringOptions("Enter currency ISO code from which to convert", "Wrong input", codes);
		String codeTo = io.readStringOptions("Enter currency ISO code to convert to", "Wrong input", codes);
		int amount = io.readInt("Enter amount of money", "Wrong input");
		double res = currencyConvertor.convert(codeFrom, codeTo, amount);
		io.writeLine("The result is " + String.format("%.2f", res));
	}
	private static void getAllCodes(InputOutput io) {
		Set<String> codes = currencyConvertor.getAllCodes();
		codes.stream().sorted().forEach(io::writeLine);
	}
}
