package censusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	private static final String PATTERN_FOR_CSV_FILE = "^[a-zA-Z0-9./_@]*[.]+[c][s][v]$";

	List<CensusDAO> censusList = null;
	Map<String, CensusDAO> censusMap = null;

	public StateCensusAnalyser() {
		this.censusMap = new HashMap<>();
		this.censusList = new ArrayList<>();
	}

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
		String extension = getFileExtension(csvFilePath);
		if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE,
					"No such a type");
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			Iterator<CSVStateCensus> stateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
			Iterable<CSVStateCensus> stateCensuses = () -> stateCensusIterator;
			StreamSupport.stream(stateCensuses.spliterator(), false)
					.forEach(csvStateCensus -> censusMap.put(csvStateCensus.getState(), new CensusDAO(csvStateCensus)));
			censusList = censusMap.values().stream().collect(Collectors.toList());
			return censusMap.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(
					CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER,
					"Delimiter or header not found");
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND,
					"File not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSVBuilderException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyserException {
		String extension = getFileExtension(csvFilePath);
		if (!Pattern.matches(PATTERN_FOR_CSV_FILE, extension))
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_TYPE,
					"No such a type");
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			IcsvBuilder csvBuilder = CsvBuilderFactory.createCsvBuilder();
			Iterator<IndianStateCode> stateCensusIterator = csvBuilder.getCSVFileIterator(reader,
					IndianStateCode.class);
			Iterable<IndianStateCode> stateCensuses = () -> stateCensusIterator;
			StreamSupport.stream(stateCensuses.spliterator(), false)
					.forEach(csvStateCensus -> censusMap.put(csvStateCensus.getState(), new CensusDAO(csvStateCensus)));
			censusList = censusMap.values().stream().collect(Collectors.toList());
			return censusMap.size();
		} catch (RuntimeException e) {
			throw new CensusAnalyserException(
					CensusAnalyserException.CensusAnalyserCustomExceptionType.WRONG_DELIMITER_OR_HEADER,
					"Delimiter or header not found");
		} catch (NoSuchFileException e) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND,
					"File not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSVBuilderException e) {
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

	// counting
	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		int recordCount = (int) StreamSupport.stream(iterable.spliterator(), false).count();
		return recordCount;
	}

	// using generic method to get csv iterator
	private <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) {
		CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
		csvToBeanBuilder.withType(csvClass);
		csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
		CsvToBean<E> csvToBean = csvToBeanBuilder.build();
		return csvToBean.iterator();
	}

	public String getSortedCensusStateData(String stateCensusDataPath) throws CensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException(
					CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA,
					"Census data not found");
		}
		Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.state);
		this.sortCSVData(censusComparator);
		String sortedStateCensusJson = new Gson().toJson(censusList);
		return sortedStateCensusJson;
	}

	public String getSortedStateCodeData() throws CensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException(
					CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_SUCH_CENSUS_DATA,
					"Census state code data not found");
		}
		Comparator<CensusDAO> stateCodeComparator = Comparator.comparing(censusDAO -> censusDAO.stateCode);
		this.sortCSVData(stateCodeComparator);
		String sortedStateCodeJson = new Gson().toJson(censusList);
		return sortedStateCodeJson;
	}

	// sorting CSV Data
	private void sortCSVData(Comparator<CensusDAO> csvComparator) {
		for (int i = 0; i < censusList.size() - 1; i++) {
			for (int j = 0; j < censusList.size() - i - 1; j++) {
				CensusDAO census1 = censusList.get(j);
				CensusDAO census2 = censusList.get(j + 1);
				if (csvComparator.compare(census1, census2) > 0) {
					censusList.set(j, census2);
					censusList.set(j + 1, census1);
				}

			}

		}
	}

	public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA,
					"No census data");
		}
		Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.population);
		this.sortCSVData(censusComparator);
		Collections.reverse(censusList);
		String sortedStateCensusJson = new Gson().toJson(censusList);
		return sortedStateCensusJson;
	}

	// method to sort state census data by population density
	public String getPopulationDensityWiseSortedCensusData() throws CensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA,
					"No census data");
		}
		Comparator<CensusDAO> censusComparator = Comparator.comparing(censusDAO -> censusDAO.densityPerSqKm);
		this.sortCSVData(censusComparator);
		Collections.reverse(censusList);
		String sortedStateCensusJson = new Gson().toJson(censusList);
		return sortedStateCensusJson;
	}

	// method to sort state census data by area
	public String getAreaWiseSortedCensusData() throws CensusAnalyserException {
		if (censusList == null || censusList.size() == 0) {
			throw new CensusAnalyserException(CensusAnalyserException.CensusAnalyserCustomExceptionType.NO_CENSUS_DATA,
					"No census data");
		}
		Comparator<CensusDAO> censusComparator = Comparator.comparing(CensusDAO->CensusDAO.areaInSqKm);
		this.sortCSVData(censusComparator);
		Collections.reverse(censusList);
		String sortedStateCensusJson = new Gson().toJson(censusList);
		return sortedStateCensusJson;
	}
}