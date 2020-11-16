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
    private static final String WRONG_HEADER_INDIAN_STATE_CODE_INFO_PATH = "./CensusAnalyserProblem/resources/IndiaStateCode.csv"; 
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
    
  //Test Case 2.2
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproper_ShouldThrowException() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        	int numberOfRecord = censusAnalyser.loadIndianStateCodeData(WRONG_INDIAN_STATE_CODE_INFO_PATH);
            assertEquals(37, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    
    //Test Case 2.3
    @Test
    public void givenIndianStateCsvFile_WhenImproperType_ShouldThrowException() throws CensusAnalyserException{
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_INDIAN_STATE_CODE_EXTENSION);
            assertEquals(37,numberOfRecord);
        } catch (CensusAnalyserException e) {
        	assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, e.type);
        }
    }
    
  //TEST CASE 2.4 
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperType_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
        	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_INDIAN_STATE_CODE_INFO_PATH);
            assertEquals(37, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }
    
  //TEST CASE 2.5
    @Test
    public void givenIndianStateCodeCsvFile_WhenImproperHeader_ShouldThrowException() throws CensusAnalyserException {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_HEADER_INDIAN_STATE_CODE_INFO_PATH);
            assertEquals(37, numberOfRecord);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, e.type);
        }
    }
    
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnFirstSortedResult() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            String sortedCensusData = censusAnalyser.getSortedCensusStateData(INDIA_CENSUS_CSV_FILE_PATH);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void givenIndianCensusData_WhenSortedOnState_ShouldReturnLastSortedResult() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            String sortedCensusData = censusAnalyser.getSortedCensusStateData(INDIA_CENSUS_CSV_FILE_PATH);
            IndianStateCode[] censusCSV = new Gson().fromJson(sortedCensusData, IndianStateCode[].class);
            assertEquals("West Bengal", censusCSV[28].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void givenTheStateCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            censusAnalyser.loadIndianStateCodeData(INDIA_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndianStateCode[] stateCSV = new Gson().fromJson(sortedCensusData,IndianStateCode[].class);
            assertEquals(199812341, stateCSV[0].getPopulation());
        } catch (CensusAnalyserException e) {
            e.getStackTrace();
        }
    }
}