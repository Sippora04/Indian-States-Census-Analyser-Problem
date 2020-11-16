package censusanalyser;

public class CensusAnalyserException extends Exception {

    public enum CensusAnalyserCustomExceptionType {
        FILE_NOT_FOUND,NO_SUCH_TYPE,WRONG_DELIMITER_OR_HEADER,NO_SUCH_CENSUS_DATA, NO_CENSUS_DATA;
    }

    public CensusAnalyserCustomExceptionType type;

    public CensusAnalyserException(CensusAnalyserCustomExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}