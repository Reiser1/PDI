import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
    /**
     * Define a method that saves data to a CSV file
     * 
     */
    public void saveData(Scanner scanner, Team[] teams){
        scanner.nextLine();
        System.out.print("What would you like to name your CSV file? "); // Prompt user for CSV file name
        String csvFile = scanner.nextLine();

        /**
         * Iterate over each team using a traditional for loop
         */
        for (int teamIndex = 0; teamIndex < teams.length; teamIndex++) {
            Team team = teams[teamIndex]; // Get the current team
            if (team.driverNumbers() > 0) { // Check if team has any drivers
                try (PrintWriter writer = new PrintWriter(new FileWriter(new File(csvFile + ".csv")))) {        // Create a PrintWriter to write to the CSV file
                    writer.println("TeamName, CarCode, DriverName, GrandPrix, PositionFinished, FastestLap");       // Write the header line to the CSV file
                    /**
                     * Iterate over each driver in the team
                     */
                    for (int driverNumber = 0; driverNumber < team.driverNumbers(); driverNumber++) {
                        Driver driver = team.getDrivers()[driverNumber];        // Get the current driver
                        String driverInfo = driver.toString();      // Get the string representation of the driver
                        /**
                         * Iterate over each race the driver has raced
                         */
                        for (int races = 0; races < driver.getRacesRaced(); races++) { 
                            GrandPrix grandPrix = driver.getGrandPrixs()[races];        // Get the current Grand Prix
                            String grandPrixInformation = grandPrix.toString();     // Get the string representation of the Grand Prix
                            String line = team.getTeamName() + ", " + team.toString() + ", " + driverInfo + ", " + grandPrixInformation;
                            System.out.println(line);
                            writer.println(line);       // Write the line to the CSV file
                        }
                    }
                    System.out.println("Data saved to " + csvFile + ".csv successfully.");
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());     // Print an error message if an IOException occurs
                }
            }
        }
    }
}