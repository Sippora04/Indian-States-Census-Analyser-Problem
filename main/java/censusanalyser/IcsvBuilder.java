package censusanalyser;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface IcsvBuilder {
	 public <E> Iterator <E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException;
	 
	 List<IndianStateCode> getCSVFileList(Reader reader, Class<IndianStateCode> indianStateCodeClass); 
}