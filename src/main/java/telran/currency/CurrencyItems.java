package telran.currency;

import java.util.*;
import telran.currency.service.CurrencyConvertor;
import telran.view.*;

public class CurrencyItems {
	private final CurrencyConvertor currencyConvertor;

    public CurrencyItems(CurrencyConvertor currencyConvertor) {
        this.currencyConvertor = currencyConvertor;
    }

	public List<Item> getItems() {
		Item[] items = { Item.of("Display strongest currencies", io -> strongestCurrencies(io, true)),
				Item.of("Display weakest currencies", io -> strongestCurrencies(io, false)),
				Item.of("Convert currencies", this::convert),
				Item.of("Get all codes", this::getAllCodes),
				Item.ofExit()};
		return new ArrayList<>(List.of(items));
	}
	private void strongestCurrencies(InputOutput io, boolean strongest) {
        int amount = (int) Math.floor(io.readNumberRange(
                "Enter the number of currencies: ",
                "Wrong input",
                1, currencyConvertor.getMaxNumberOfCurrencies()));
        List<String> res = strongest ? currencyConvertor.strongestCurrencies(amount) : currencyConvertor.weakestCurrencies(amount);
        io.writeLine(strongest ? "Top strongest currencies: " : "Top weakest currencies: ");
        res.forEach(io::writeLine);
    }
	private void convert(InputOutput io) {
		HashSet<String> codes = currencyConvertor.getAllCodes();
		String codeFrom = io.readStringOptions("Enter currency ISO code from which to convert", "Wrong input", codes);
		String codeTo = io.readStringOptions("Enter currency ISO code to convert to", "Wrong input", codes);
		int amount = io.readInt("Enter amount of money", "Wrong input");
		double res = currencyConvertor.convert(codeFrom, codeTo, amount);
		io.writeLine("The result is " + String.format("%.2f", res));
	}
	private void getAllCodes(InputOutput io) {
		Set<String> codes = currencyConvertor.getAllCodes();
		codes.stream().sorted().forEach(io::writeLine);
	}
}
