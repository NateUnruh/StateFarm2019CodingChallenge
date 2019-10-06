package codingcompetition2019;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;

public class CodingCompCSVUtil {
	public List<List<String>> readCSVFileByCountry(String fileName, String countryName) throws IOException {
		// read in line by line, make sure line has countryName
		List<List<String>> csvRecord = new ArrayList<List<String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null){
				if(line.contains(countryName)){
					String [] disaster = line.split(cvsSplitBy);
					csvRecord.add(Arrays.asList(disaster));
				}

			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return csvRecord;
	}
	
	public List<List<String>> readCSVFileWithHeaders(String fileName) throws IOException {
		// Read in using FileReader don't skip first line
		List<List<String>> csvRecord = new ArrayList<List<String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(fileName));
			while((line = br.readLine()) != null){
				String [] disaster = line.split(cvsSplitBy);
				csvRecord.add(Arrays.asList(disaster));


			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return csvRecord;
	}
	
	public List<List<String>> readCSVFileWithoutHeaders(String fileName) throws IOException {
		// Read in using filereader, skip first line
		List<List<String>> csvRecord = new ArrayList<List<String>>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try{
			br = new BufferedReader(new FileReader(fileName));
			line = br.readLine();
			while((line = br.readLine()) != null){
				String [] disaster = line.split(cvsSplitBy);
				csvRecord.add(Arrays.asList(disaster));


			}
		}catch(IOException e){
			e.printStackTrace();
		}

		return csvRecord;
	}
	
	public DisasterDescription getMostImpactfulYear(List<List<String>> records) {
		// Create HashMap to store disasters to iterate through to find max in that category
		HashMap<DisasterDescription,Integer> disasters = new HashMap<DisasterDescription, Integer>();
		// Iterate through records, add to HashMap if correct year
		for (List<String> i : records){
			disasters.put(new DisasterDescription(i.get(0),i.get(1),(i.get(2))),Integer.parseInt(i.get(3)));
		}

		// Find max Entry by looping through and comparing values
		Map.Entry<DisasterDescription,Integer> maxEntry = null;
		for(Map.Entry<DisasterDescription,Integer> entry : disasters.entrySet()){
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();


	}

	public DisasterDescription getMostImpactfulYearByCategory(String category, List<List<String>> records) {
		// Create HashMap to store disasters to iterate through to find max in that category
		HashMap<DisasterDescription,Integer> disasters = new HashMap<DisasterDescription, Integer>();
		// Iterate through records, add to HashMap if correct disaster
		for (List<String> i : records){
			if(i.get(0).equals(category)){
				disasters.put(new DisasterDescription(i.get(0),i.get(1),(i.get(2))),Integer.parseInt(i.get(3)));
			}

		}

		// Find max Entry by looping through and comparing values
		Map.Entry<DisasterDescription,Integer> maxEntry = null;
		for(Map.Entry<DisasterDescription,Integer> entry : disasters.entrySet()){
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();

	}

	public DisasterDescription getMostImpactfulDisasterByYear(String year, List<List<String>> records) {
		// Create HashMap to store disasters to iterate through to find max
		HashMap<DisasterDescription,Integer> disasters = new HashMap<DisasterDescription, Integer>();
		// Iterate through records, add to HashMap if right year and not "All natural disasters" since most to find type
		for(List<String> i : records){
			if(i.get(2).equals(year) && !i.get(0).equals("All natural disasters")){
				DisasterDescription disaster = new DisasterDescription(i.get(0),i.get(1),Integer.parseInt(i.get(3)));

				disaster.setNum(Integer.parseInt(i.get(3)));
				disasters.put(disaster,disaster.getReportedIncidentsNum());
			}
		}

		// Find max Entry by looping through and comparing values
		Map.Entry<DisasterDescription,Integer> maxEntry = null;
		for(Map.Entry<DisasterDescription,Integer> entry : disasters.entrySet()){
			if(maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0){
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}

	public DisasterDescription getTotalReportedIncidentsByCategory(String category, List<List<String>> records) {
		int num = 0;
		for(List<String> i : records){
			if(i.get(0).equals(category)){
				num += Integer.parseInt(i.get(3));
			}
		}
		return new DisasterDescription(category,"",num);
	}
	
	/**
	 * This method will return the count if the number of incident falls within the provided range.
	 * To simplify the problem, we assume:
	 * 	+ A value of -1 is provided if the max range is NOT applicable.
	 *  + A min value can be provided, without providing a max value (which then has to be -1 like indicated above).
	 *  + If a max value is provided, then a max value is also needed.
	 */
	public int countImpactfulYearsWithReportedIncidentsWithinRange(List<List<String>> records, int min, int max) {
		int num = 0;
		// Change max to max Integer value so can use same comparison
		if (max == -1){
			max = Integer.MAX_VALUE;
		}
		for (List<String> i : records){
			if(Integer.parseInt(i.get(3)) >= min && Integer.parseInt(i.get(3)) <= max){
				num++;

			}
		}
		return num;
	}

	// Returns true if records1 has more disasters, false if records2 has more disasters.
	public boolean firstRecordsHaveMoreReportedIndicents(List<List<String>> records1, List<List<String>> records2) {
		int records1amount = 0;
		for(List<String> i : records1){
			records1amount += Integer.parseInt(i.get(3));
		}
		int records2amount = 0;
		for(List<String> i : records2){
			records2amount += Integer.parseInt(i.get(3));
		}
		return records1amount > records2amount;
	}
}
