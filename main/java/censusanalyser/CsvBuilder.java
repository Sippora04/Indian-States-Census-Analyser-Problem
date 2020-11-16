package censusanalyser;

import java.util.Iterator;
import java.util.List;
import java.io.Reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvBuilder implements IcsvBuilder {
    
	public <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(CensusAnalyserException.CensusAnalyserCustomExceptionType.FILE_NOT_FOUND, "Wrong file");
        }
    }

    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        return null;
    }

    @Override
    public List<IndianStateCode> getCSVFileList(Reader reader, Class<IndianStateCode> indianStateCodeClass) {
        return null;
    }
}