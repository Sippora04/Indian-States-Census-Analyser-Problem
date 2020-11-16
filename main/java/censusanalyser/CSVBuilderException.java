package censusanalyser;

public class CSVBuilderException extends Exception {
	public CensusAnalyserException.CensusAnalyserCustomExceptionType type;
	
	enum Exception_type {
		UNABLE_TO_PARSE;
	}
	
	public CSVBuilderException(CensusAnalyserException.CensusAnalyserCustomExceptionType, String message) {
		super(message);
		this.type = type;
	}
}
