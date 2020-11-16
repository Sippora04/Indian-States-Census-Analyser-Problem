package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$"; 
	
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		 int numOfRecord = 0;
		 String extension = getFileExtension(csvFilePath);
		 if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
			 throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
		 try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			 Iterator<IndianStateCode> censusCSVIterator = new CsvBuilder().getCSVFileIterator(reader, IndianStateCode.class);
			 while (censusCSVIterator.hasNext()) {
				 numOfRecord++;
	             IndianStateCode censusCSV = censusCSVIterator.next();
			 }
			 return this.getCount(censusCSVIterator);
		 } catch (NoSuchFileException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
		 } catch (RuntimeException e) {
			 throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return numOfRecord;
	 }
	 
	 public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
		 int numOfRecord = 0;
		 String extension = getFileExtension(csvFilePath);
		 if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such type");
		 try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			 Iterator<IndianStateCode> statesCSVIterator = new CsvBuilder().getCSVFileIterator(reader, IndianStateCode.class);
			 return this.getCount(statesCSVIterator);
		 } catch (NoSuchFileException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
		 } catch (RuntimeException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
		 } catch (IOException e) {
	            e.printStackTrace();
	     }
		 return numOfRecord;
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
	 
	 private <E> int getCount(Iterator<E> iterator) {
		 Iterable<E> iterable = () -> iterator;
	     int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
	     return recordCount;
	 }
	 
	 private <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) {
		 CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
	     csvToBeanBuilder.withType(csvClass);
	     csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
	     CsvToBean<E> csvToBean = csvToBeanBuilder.build();
	     return csvToBean.iterator();
	 }
}