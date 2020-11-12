package censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateCensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_FILE_EXTENSION = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/main/resources/IndiaStateCensusData.txt";
    private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "C:/Users/sippo/eclipse-workspace/CensusAnalyserProblem/main/resources/IndiaStateCensusData.csv";
    
    //Test Case 1.1
    @Test
    public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws CensusAnalyserException {
    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
    	int numOfRecord = 0;
    	try {
        	numOfRecord = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
        	e.printStackTrace();
        }
        assertEquals(29, numOfRecord);
    }


    //Test Case 1.2
    @Test
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
            assertEquals(29, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM,e.type);
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
           assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_TYPE,e.type);
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
            assertEquals(CensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
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
            assertEquals(CensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
        }
    }
}