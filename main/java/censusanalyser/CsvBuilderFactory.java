package censusanalyser;

public class CsvBuilderFactory {
	public static IcsvBuilder createCsvBuilder() {
        return new CsvBuilder();
    }
}
