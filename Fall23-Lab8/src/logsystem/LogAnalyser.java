package logsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogAnalyser {
	private List<String> logs;
	
	public LogAnalyser(String file) {
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


	public void getErrors() {
		Pattern error = Pattern.compile("ERROR");
		
		List<String> errorLines = logs.stream()
				.filter(line -> error.matcher(line).find())
				.collect(Collectors.toList());
		
		System.out.println("Total: " + errorLines.size());
		
		errorLines.stream()
			.reduce((first, second) -> first)
	        .ifPresent(System.out::println);
		
		errorLines.stream()
	        .reduce((first, second) -> second)
	        .ifPresent(System.out::println);
				
	}
	
	public void getWarnings() {
		Pattern warning = Pattern.compile("WARN");
		
		List<String> warnLines = logs.stream()
				.filter(line -> warning.matcher(line).find())
				.collect(Collectors.toList());
		
		System.out.println("Total: " + warnLines.size());
		
		warnLines.stream()
			.reduce((first, second) -> first)
	        .ifPresent(System.out::println);
		
		warnLines.stream()
	        .reduce((first, second) -> second)
	        .ifPresent(System.out::println);
	}
	
	public void getInfos() {
		Pattern info = Pattern.compile("INFO");
		
		List<String> infoLines = logs.stream()
				.filter(line -> info.matcher(line).find())
				.collect(Collectors.toList());
		
		System.out.println("Total: " + infoLines.size());
		
		infoLines.stream()
			.reduce((first, second) -> first)
	        .ifPresent(System.out::println);
		
		infoLines.stream()
	        .reduce((first, second) -> second)
	        .ifPresent(System.out::println);
	}
	
	public void getEntriesInTimeFrame(String startTime, String endTime) {
		Pattern check = Pattern.compile("\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\]");
		
		List<String> timeLines = logs.stream()
				.filter(line -> {
					Matcher matcher = check.matcher(line);
					
					if (matcher.find()) {
						String time = matcher.group(1);
						return time.compareTo(startTime) >= 0 && time.compareTo(endTime) <= 0;
					}
					return false;
				})
				.collect(Collectors.toList());
		
		timeLines.stream()
		.reduce((first, second) -> first)
        .ifPresent(System.out::println);
	
		timeLines.stream()
        .reduce((first, second) -> second)
        .ifPresent(System.out::println);
	}
	
	public void getEntriesThatContain(String phrase) {
		List<String> entryLines = logs.stream()
				.filter(line -> line.contains(phrase))
				.collect(Collectors.toList());
		
		System.out.println("Total: " + entryLines.size());
		
		entryLines.stream()
			.reduce((first, second) -> first)
	        .ifPresent(System.out::println);
		
		if(entryLines.size() > 1) {
			entryLines.stream()
		        .reduce((first, second) -> second)
		        .ifPresent(System.out::println);
		}
	}
}
