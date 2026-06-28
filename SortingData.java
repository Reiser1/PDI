import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortingData {
    
    /**
     * This method will sort the teams based on the fastest lap times
     */
    public void sortingTeams(Team[] teamsArray, int teamNumber) {
        //Team temp;

        // Calculate the combined fastest times for each team
        for (int i = 0; i < teamNumber; i++) {
            for (int j = i + 1; j < teamNumber; j++) {
                if (teamsArray[i].getName().equals(teamsArray[j].getName())) {
                    double combinedFastestTime = teamsArray[i].getFastestLap() + teamsArray[j].getFastestLap();
                    teamsArray[i].addFastestTime(combinedFastestTime);
                    teamsArray[j].addFastestTime(combinedFastestTime);
                }
            }
        }
        
        // Calculate the combined fastest times for each team
        for (int i = 0; i < teamNumber; i++) {
            for (int j = i + 1; j < teamNumber; j++) {
                if (teamsArray[i] != null && teamsArray[j] != null && teamsArray[i].getName().equals(teamsArray[j].getName())) {
                    // Only add j's time to i, and then nullify j to avoid further comparisons.
                    teamsArray[i].addFastestTime(teamsArray[j].getFastestLap());
                    teamsArray[j] = null;
                }
            }
        }

        // for (int i = 0; i < teamNumber - 1; i++) {
        //     for (int j = 0; j < teamNumber - 1 - i; j++) {
        //         if (teamsArray[j] != null && teamsArray[j + 1] != null && teamsArray[j].getFastestLap() > teamsArray[j + 1].getFastestLap()) {
        //             temp = teamsArray[j];
        //             teamsArray[j] = teamsArray[j + 1];
        //             teamsArray[j + 1] = temp;
        //         }
        //     }
        // }

        // Remove null entries and create a new array for sorting
        Team[] filteredTeams = Arrays.stream(teamsArray)
                                    .filter(team -> team != null)
                                    .toArray(Team[]::new);

        // Sort the array using Arrays.sort with a custom comparator for descending order
        Arrays.sort(filteredTeams, Comparator.comparingDouble(Team::getFastestLap).reversed());

        printSortedTeams(filteredTeams, teamsArray);
    }

    /**
     * This method will print the sorted list of teams
     */
    private void printSortedTeams(Team[] filteredTeams, Team[] teamsArray){
        System.out.println();
        System.out.println("The list of teams which finished the race based on fastest times in descending order are:");
        for (int i = 0; i < filteredTeams.length; i++) {
            Team team = filteredTeams[i];
            // Display the sorted list of teams if the team is not null
            if (team != null) {
                System.out.println("> " + team.getName() + " - " + team.getFastestLap());
            }
        }

        // for (int i = 0; i < teamsArray.length; i++) {
        //     if (teamsArray[i] != null) {
        //         System.out.println("> " + teamsArray[i].getName() + " - " + teamsArray[i].getFastestLap());
        //     }
        // }
    }

    /**
     * This method will sort the drivers based on the fastest lap times
     */
    public void driverSorting(Scanner scanner, Driver[] driverArray, String raceName, int choice){
        Driver[] driversArray = new Driver[driverArray.length];
        int driverNumber = 0;

        System.out.println("Here is a list of all the drivers that raced in " + raceName + ":");
        System.out.println();

        for (int i = 0; i < driverArray.length; i++) {
            GrandPrix[] races = driverArray[i].getGrandPrixs();
            for (int j = 0; j < driverArray[i].getRacesRaced(); j++) {
                if (races[j].getName().equals(raceName)) {
                    // Checks if the driver did not finish
                    if (races[j].getFastestLap() == 205.5){
                        System.out.println("> " + driverArray[i].getName() + " - DNF");
                    }
                    else {
                        driversArray[driverNumber] = new Driver(driverArray[i].getName());
                        driversArray[driverNumber].addFastestLap(races[j].getFastestLap());
                        System.out.println("> " + driversArray[driverNumber] + " - " + driversArray[driverNumber].getFastestLap());
                        driverNumber++;
                    }
                }
            }
        }

        // Sort the drivers based on the fastest lap times in descending order
        if (choice == 2){
            sortDescendingOrder(scanner, driverArray, driversArray, driverNumber);
        }
        // Sort the drivers based on the fastest lap times in ascending order
        else if (choice == 3){
            sortAscendingOrder(scanner, driverArray, driversArray, driverNumber);
        }
    }

    /**
     * This method will sort the drivers based on the fastest lap times in descending order
     */
    private void sortDescendingOrder(Scanner scanner, Driver[] driverArray, Driver[] driversArray, int driverNumber) {
        for (int i = 0; i < driverNumber - 1; i++) {
            for (int j = 0; j < driverNumber - i - 1; j++) {
                if (driversArray[j].getFastestLap() < driversArray[j + 1].getFastestLap()) {
                    Driver temp = driversArray[j];
                    driversArray[j] = driversArray[j + 1];
                    driversArray[j + 1] = temp;
                }
            }
        }
    
        // Display the sorted list of drivers
        System.out.println();
        System.out.println("Here is the list of drivers based on fastest lap times in descending order: ");
        System.out.println();
        for (int i = 0; i < driverNumber; i++) {
            System.out.println("> " + driversArray[i].getName() + " - " + driversArray[i].getFastestLap());
        }
    }

    /**
     * This method will sort the drivers based on the fastest lap times in ascending order
     */
    private void sortAscendingOrder(Scanner scanner, Driver[] driverArray, Driver[] driversArray, int driverNumber) {
        for (int i = 0; i < driverNumber - 1; i++) {
            for (int j = 0; j < driverNumber - i - 1; j++) {
                if (driversArray[j].getFastestLap() > driversArray[j + 1].getFastestLap()) {
                    Driver temp = driversArray[j];
                    driversArray[j] = driversArray[j + 1];
                    driversArray[j + 1] = temp;
                }
            }
        }
    

        // Display the sorted list of drivers
        System.out.println();
        System.out.println("Here is the list of drivers based on fastest lap times in ascending order:");
        System.out.println();
        for (int i = 0; i < driverNumber; i++) {
            System.out.println("> " + driversArray[i].getName() + " - " + driversArray[i].getFastestLap());
        }
    }
}