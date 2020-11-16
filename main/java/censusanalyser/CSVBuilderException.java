package censusanalyser;

public class CSVBuilderException extends Exception {

    public CensusAnalyserException.CensusAnalyserCustomExceptionType type;

    enum Exception_Type {
        UNABLE_TO_PARSE;
    }
    
    public CSVBuilderException(CensusAnalyserException.CensusAnalyserCustomExceptionType type,String message) {
        super(message);
        this.type = type;
    }
}