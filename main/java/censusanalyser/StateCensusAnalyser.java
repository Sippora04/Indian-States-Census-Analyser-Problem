package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$"; 
	
	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		 //int numOfRecord = 0;
		 String extension = getFileExtension(csvFilePath);
		 if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
			 throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such a type");
		 try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			 IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			 List<IndianStateCode> csvFileList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
			 return csvFileList.size();
		 } catch (NoSuchFileException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
		 } catch (RuntimeException e) {
			 throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "Delimiter or header not found");
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
		 return 0;
	 }
	 
	 public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
		 //int numOfRecord = 0;
		 String extension = getFileExtension(csvFilePath);
		 if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE, "No such type");
		 try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			 IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			 List<IndianStateCode> csvFileList = csvBuilder.getCSVFileList(reader, IndianStateCode.class);
			 return csvFileList.size();
		 } catch (NoSuchFileException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "File not found");
		 } catch (RuntimeException e) {
	            throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER, "No such delimiter and header");
		 } catch (IOException e) {
	            e.printStackTrace();
	     }
		 return 0;
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
	 
	 //counting
	 private <E> int getCount(Iterator<E> iterator) {
		 Iterable<E> iterable = () -> iterator;
	     int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
	     return recordCount;
	 }
	
	 //using generic method to get csv iterator
	 private <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) {
		 CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
	     csvToBeanBuilder.withType(csvClass);
	     csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
	     CsvToBean<E> csvToBean = csvToBeanBuilder.build();
	     return csvToBean.iterator();
	 }
	 
	public String getSortedCensusStateData(String csvFilePath) throws CensusAnalyserException {
		loadIndiaCensusData(csvFilePath);
		if (csvFileList == null || csvFileList.size() == 0) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA, "Census data not found");
		}
		csvFileList.sort(Comparator.comparing(e -> e.getState()));
		String toJson = new Gson().toJson(csvFileList);
		return toJson;
	}
 
	private void sort(List<IndianStateCode> csvFileList, Comparator<IndianStateCode> censusComparator) {
		for (int i = 0; i < csvFileList.size(); i++) {
			for (int j = 0; j < csvFileList.size() - i - 1; j++) {
				IndianStateCode census1 = csvFileList.get(j);
				IndianStateCode census2 = csvFileList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					csvFileList.set(j, census2);
					csvFileList.set(j + 1, census1);
				}
			}
		}
	}
}