package censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateCensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_EXTENSION = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/resources/IndiaStateCensusData.txt";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/resources/IndiaStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/resources/IndiaStateCensusData.csv";
    private static final String INDIAN_STATE_CODE_INFORMATION_PATH = "./CensusAnalyserProblem/resources/IndiaStateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_INFO_PATH = "./CensusAnalyserProblem/main/resources/IndiaStateCode.csv";
    private static final String WRONG_INDIAN_STATE_CODE_EXTENSION = "./CensusAnalyserProblem/resources/IndiaStateCode.txt";
    private static final String WRONG_DELIMITER_INDIAN_STATE_CODE_INFO_PATH = "./CensusAnalyserProblem/resources/IndiaStateCode.csv";
    private static final String WRONG_HEADER_INDIAN_STATE_CODE_INFOR_PATH = "./CensusAnalyserProblem/resources/IndiaStateCode.csv"; 
    //Test Case 1.1
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws CensusAnalyserException {
    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
    	int numberOfRecord = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        assertEquals(29, numberOfRecord);
    }


    //Test Case 1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            assertEquals(29, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
  //TEST CASE 1.3
    @Test
    public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() throws CensusAnalyserException{
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_FILE_EXTENSION);
            assertEquals(29,numberOfRecord);
        } catch (CensusAnalyserException e) {
        	assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }

    //Test Case 1.4
    @Test
    public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
            assertEquals(29, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
        }
    }

    //Test Case 1.5
    @Test
    public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
            assertEquals(29, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
        }
    }
    
  //Test Case 2.1
    @Test
    public void givenIndianStateCodeCsvFile_WhenProper_ShouldReturnCorrectRecordCount() throws CensusAnalyserException {
    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
    	int numberOfRecord = censusAnalyser.loadIndianStateCodeData(INDIAN_STATE_CODE_INFORMATION_PATH);
        assertEquals(37, numberOfRecord);
    }
}