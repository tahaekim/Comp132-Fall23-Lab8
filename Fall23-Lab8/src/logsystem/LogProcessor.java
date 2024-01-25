package logsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LogProcessor {
	private ArrayList<String> logs;
	
	public LogProcessor(String file) {
		logs = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null ) {
				logs.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getStatistics() {
		double averageLength = logs.stream()
                .mapToInt(String::length)
                .average()
                .orElse(Double.NaN);

        String longestEntry = logs.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("N/A");

        String shortestEntry = logs.stream()
                .min(Comparator.comparingInt(String::length))
                .orElse("N/A");

        System.out.println("Average length: " + averageLength);
        System.out.println("Longest entry (" + longestEntry.length() + " chars): " + longestEntry);
        System.out.println("Shortest entry (" + shortestEntry.length() + " chars): " + shortestEntry);
	}
	
	public void getCroppedEntries(int maxLength) {
	    try {
	        List<String> result = logs.stream()
	        		.filter(line -> line.length() > maxLength)
	        		.map(line -> line.substring(0, maxLength) + "...")
					.collect(Collectors.toList());

	        result.stream()
			.reduce((first, second) -> first)
	        .ifPresent(System.out::println);
		
			result.stream()
	        .reduce((first, second) -> second)
	        .ifPresent(System.out::println);
			
	    } catch (Exception e) {
	    	e.getStackTrace();
	    }
	}

	public void sortByDate() {
		List<String> sortedLogs = logs.stream()
                .sorted(Comparator.comparing(entry -> entry.substring(1, 20)))
                .collect(Collectors.toList());

        System.out.println("First entry: " + sortedLogs.get(0));
        System.out.println("Last entry: " + sortedLogs.get(sortedLogs.size() - 1));
	}
	
}
