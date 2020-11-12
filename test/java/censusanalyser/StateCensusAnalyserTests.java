package censusanalyser;

import static org.junit.Assert.*;

import org.junit.Test;

public class StateCensusAnalyserTests {
	
	//CONSTANTS	
	private static final String STATE_CENSUS_DATA_PATH = "C:\\Users\\sippo\\eclipse-workspace\\CensusAnalyserProblem\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_STATE_CENSUS_DATA_PATH = "C:\\Users\\sippo\\eclipse-workspace\\CensusAnalyserProblem\\src\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_TYPE_STATE_CENSUS_DATA_PATH = "C:\\Users\\sippo\\eclipse-workspace\\CensusAnalyserProblem\\resources\\IndiaStateCensusData.txt";
	private static final String WRONG_DELIMITER_STATE_CENSUS_DATA_PATH = "C:\\Users\\sippo\\eclipse-workspace\\CensusAnalyserProblem\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_HEADER_STATE_CENSUS_DATA_PATH = "C:\\Users\\sippo\\eclipse-workspace\\CensusAnalyserProblem\\resources\\IndiaStateCensusData.csv";
   
	//Test Case 1.1
	@Test
	public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecord() throws CensusAnalyserException {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		int numberOfRecord = censusAnalyser.loadIndiaCensusData(STATE_CENSUS_DATA_PATH);
		assertEquals(29, numberOfRecord);
	}

	//Test Case 1.2
	@Test
	public void givenIndianCensusCsvFile_WhenProper_ShouldReturnCorrectRecordWrong_ThrowCustomException() throws CensusAnalyserException{
	    try {
	    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	    	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_STATE_CENSUS_DATA_PATH);
	        assertEquals(29, numberOfRecord);
	    }catch (CensusAnalyserException e){
	        assertEquals(CensusAnalyserException.ExceptionType.FILE_NOT_FOUND,e.type);
	    }
	}

	//Test Case 1.3
	@Test
	public void givenIndianCensusCsvFile_WhenImproperType_ShouldThrowException() throws CensusAnalyserException{
	    try {
	    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	    	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_TYPE_STATE_CENSUS_DATA_PATH);
	        assertEquals(29,numberOfRecord);
	    } catch (CensusAnalyserException e) {
	        assertEquals(CensusAnalyserException.ExceptionType.NO_SUCH_TYPE,e.type);
	    }
	}

	//Test Case 1.4
	@Test
	public void givenIndianCensusCsvFile_WhenImproperDelimiter_ShouldThrowException() throws CensusAnalyserException{
	    try {
	    	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
	    	int numberOfRecord = censusAnalyser.loadIndiaCensusData(WRONG_DELIMITER_STATE_CENSUS_DATA_PATH);
	        assertEquals(29,numberOfRecord);
	    } catch (CensusAnalyserException e) {
	        assertEquals(CensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
	    }
	}

	//TEST CASE 1.5
	@Test
	public void givenIndianCensusCsvFile_WhenImproperHeader_ShouldThrowException() throws CensusAnalyserException {
	    try {
	    	StateCensusAnalyser censusAnalyserProblem = new StateCensusAnalyser();
	    	int numberOfRecord = censusAnalyserProblem.loadIndiaCensusData(WRONG_HEADER_STATE_CENSUS_DATA_PATH);
	    	assertEquals(29,numberOfRecord);
	    } catch (CensusAnalyserException e) {
	        assertEquals(CensusAnalyserException.ExceptionType.WRONG_DELIMITER_OR_HEADER,e.type);
	    }
	}
}