package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStates {

	private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";
	
	public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
		int recordCount = 0;
        String extension = getFileExtension(csvFilePath);
        if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
        int numOfCount = recordCount;
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
        	CsvToBean<IndianStateCode> csvToBean = new CsvToBeanBuilder(reader).withType(IndianStateCode.class).withIgnoreLeadingWhiteSpace(true).build();
        	Iterator<IndianStateCode> statesCSVIterator = csvToBean.iterator();
        	while (statesCSVIterator.hasNext()) {
                IndianStateCode censusCSV = statesCSVIterator.next();
                ++numOfCount;
                System.out.print("SrNo: " + censusCSV.getSrNo() + ", ");
                System.out.print("state: " + censusCSV.getState() + ", ");
                System.out.print("Name: " + censusCSV.getName() + ", ");
                System.out.print("TIN: " + censusCSV.getTin() + ", ");
                System.out.print("StateCode: " + censusCSV.getStateCode() + ", ");
                System.out.println();
            }
        }  catch (NoSuchFileException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numOfCount;
	}
	
	private static String getFileExtension(String file) {
    	String extension = "";
        try {
            if (file != null)
                extension = file.substring(file.lastIndexOf("."));
        } catch (Exception e) {
            extension = "";
        }
        return extension;
    }
}
