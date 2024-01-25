package main;
import java.io.*;
import java.util.List;
import logsystem.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/server_logs.txt";
		LogAnalyser logAnalyser = new LogAnalyser(filePath);
		LogProcessor logProcessor = new LogProcessor(filePath);

		// Testing LogAnalyser methods
		System.out.println("---- LogAnalyser Tests ----");

		System.out.println("\nError Entries:");
		logAnalyser.getErrors();

		System.out.println("\nWarning Entries:");
		logAnalyser.getWarnings();

		System.out.println("\nInfo Entries:");
		logAnalyser.getInfos();

		System.out.println("\nEntries in Time Frame [2023-03-24 00:00:00, 2023-03-25 00:00:00]:");
		logAnalyser.getEntriesInTimeFrame("2023-03-24 00:00:00", "2023-03-25 00:00:00");

		System.out.println("\nEntries that Contain 'User 2415':");
		logAnalyser.getEntriesThatContain("User 2415");

		// Testing LogProcessor methods
		System.out.println("\n---- LogProcessor Tests ----");
		System.out.println("\nStatistics:");
		logProcessor.getStatistics();

		System.out.println("\nCropped Entries (maxLength 64):");
		logProcessor.getCroppedEntries(64);

		System.out.println("\nSorted By Date:");
		logProcessor.sortByDate();
        
     System.out.println("\n \n -------------------------- In Lab --------------------------\n");    
        
        try {
            String filePath1 = "src/server_logs.txt";
            List<LogRecord> failedLogRecords = LogRecord.readAndSortFailedLogRecords(filePath1);
            
// Display the output in the console            
          System.out.println("Failed Log Records sorted by message length: \n");
          for (LogRecord record : failedLogRecords) {
              System.out.println(record.getLogMessage());
          }

// Write the sorted failed log records to the output file  
            String outputFilePath = "src/Smith.txt";           
            writeToFile(outputFilePath, "Failed Log Records sorted by message length:\n");
            for (LogRecord record : failedLogRecords) {
                writeToFile(outputFilePath, record.getLogMessage() + "\n");
            }
            System.out.println("Output written to: " + outputFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(content);
        }
    }
} 