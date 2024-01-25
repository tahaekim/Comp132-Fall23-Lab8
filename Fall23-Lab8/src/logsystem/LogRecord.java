package logsystem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class LogRecord implements Comparable<LogRecord>{
	private String logEntry;

	public LogRecord(String logEntry) {
		this.logEntry = logEntry;
	}
	
	public static List<LogRecord> readAndSortFailedLogRecords(String filePath){
        List<LogRecord> failedLogRecords = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("Failed")) {
                    LogRecord logRecord = new LogRecord(line);
                    failedLogRecords.add(logRecord);
                }
            }
        }catch (IOException e) {
        	e.getStackTrace();
		}
        Collections.sort(failedLogRecords);
        return failedLogRecords;
    }
	
	
	public String getLogMessage() {
        int messageStartIndex = logEntry.indexOf(" - ") + 3;
        return logEntry.substring(messageStartIndex);
    }

	
	public int compareTo(LogRecord other) {
        return Integer.compare(this.getLogMessage().length(), other.getLogMessage().length());
    }
}
