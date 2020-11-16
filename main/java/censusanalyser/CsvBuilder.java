package censusanalyser;

import java.util.Iterator;
import java.util.List;
import java.io.Reader;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CsvBuilder implements IcsvBuilder {
	
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            return csvToBean.iterator();
		} catch (IllegalStateException e) {
            throw new CSVBuilderException();
        }
	}
}