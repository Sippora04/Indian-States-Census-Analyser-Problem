package censusanalyser;

@SuppressWarnings("serial")
public class CensusAnalyserException extends Exception {

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, NO_SUCH_TYPE, WRONG_DELIMITER_OR_HEADER, FILE_NOT_FOUND
    }

    public ExceptionType type;

    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
