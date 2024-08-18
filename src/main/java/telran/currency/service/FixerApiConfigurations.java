package telran.currency.service;

public interface FixerApiConfigurations {
	public static final String uriString = "https://data.fixer.io/api/latest?access_key=80e4b2df2b69e4d9dbc0894827c31186";
	public static final String FILE_NAME = "ratesLocal.data";
	public static final long UPDATE_INTERVAL_SECONDS = 24 * 60 * 60;
	public static final String TIMESTAMP = "timestamp";
	public static final String RATES = "rates";
}
