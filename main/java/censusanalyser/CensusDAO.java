package censusanalyser;

public class CensusDAO {
	public String state;
	public String population;
	public String area;
	public String density;
	public String stateCode;
	public String densityPerSqKm;
	public String areaInSqkm;

	public CensusDAO(CSVStateCensus csvStateCensus) {
		this.state = csvStateCensus.getState();
		this.population = csvStateCensus.getPopulation();
		this.area = csvStateCensus.getAreaInSqKm();
		this.density = csvStateCensus.getDensityPerSqKm();
	}

	public CensusDAO(IndianStateCode csvStateCode) {
		this.stateCode = csvStateCode.getStateCode();
	}
}