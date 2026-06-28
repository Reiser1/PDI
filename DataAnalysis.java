import java.util.Scanner;

public class DataAnalysis {
    /**
     * This method will print the fastest time of each team in the Grand Prix
     */
    public static void allTeamsAnalysis(Scanner scanner, Driver[] driverArray, String raceName) {
        SortingData SortingData = new SortingData();
        Team[] teamsArray = new Team[driverArray.length];
        int teamNumber = 0;
        
        /**
         * This loop iterates over the drivers and their Grand Prixs to find the fastest time of each team
         */
        for (int i = 0; i < driverArray.length; i++) {
            GrandPrix[] races = driverArray[i].getGrandPrixs();
            /**
             * This loop iterates over the Grand Prixs of the driver to find the fastest time of each team
             */
            for (int j = 0; j < driverArray[i].getRacesRaced(); j++) {
                if (races[j].getName().equals(raceName)) {
                    if (races[j].getFastestLap() == 205.5){
                        System.out.println(driverArray[i] + " from " + driverArray[i].getTeamName() + " didn't finish the Grand Prix.");
                    } else{
                        teamsArray[teamNumber] = new Team(driverArray[i].getTeamName());
                        teamsArray[teamNumber].addFastestTime(races[j].getFastestLap());
                        teamNumber++;
                    }
                    }
                }
            }
        SortingData.sortingTeams(teamsArray, teamNumber);
    }  

    /**
     * This method will find the fastest driver in the Grand Prix
     */
    public boolean findingTeamRace(Scanner scanner, Driver[] driverArray, String carCodeTeam, String raceName, Print print){
        String driver1 = "";
        String driver2 = "";
        double fastestLap1 = Double.MAX_VALUE; 
        double fastestLap2 = Double.MAX_VALUE; 
        String teamName1 = "";
        boolean raceFound = true;
        boolean carCodeFound = false;

        /**
         * This loop iterates over the drivers to find the fastest driver in the Grand Prix
         */
        for (int i = 0; i < driverArray.length; i++) {
            Driver driver = driverArray[i];
            if (driver.getCarCode().equals(carCodeTeam)) {
                carCodeFound = true;
                teamName1 = driverArray[i].getTeamName();
                GrandPrix[] races = driverArray[i].getGrandPrixs();
                /**
                 * This loop iterates over the Grand Prixs of the driver to find the fastest driver in the Grand Prix
                 */
                for (int j = 0; j < driverArray[i].getRacesRaced(); j++) {
                    /**
                     * This if statement checks if the driver has raced in the Grand Prix
                     */
                    if (races[j].getName().equals(raceName)) {
                        raceFound = false;
                        double driverFastestLap = races[j].getFastestLap();
                        if (driverFastestLap < fastestLap1) {
                            fastestLap2 = fastestLap1;
                            fastestLap1 = driverFastestLap;
                            driver1 = driverArray[i].getName();
                            System.out.println();

                            if (driverFastestLap == 205.5){
                                System.out.println();
                                System.out.print(driverArray[i] + " did not finish the race.");
                            }
                        } else if (driverFastestLap < fastestLap2) {
                            fastestLap2 = driverFastestLap;
                            driver2 = driverArray[i].getName();
                            System.out.println();

                            if (driverFastestLap == 205.5){
                                System.out.println();
                                System.out.print(driverArray[i] + " did not finish the race.");
                            }
                        }
                    }
                }
            }
        }
        if (carCodeFound && !raceFound){
            print.printSingleTeamAnalysis(scanner, driverArray, driver1, driver2, raceName, carCodeTeam, fastestLap1, fastestLap2);
        }
        else if (carCodeFound){
            System.out.println();
            System.out.println("Error. " + teamName1 + " didn't compete in the " + raceName + " Grand Prix.");
        }
        else if (!carCodeFound) {
            System.out.println();
            System.out.println("Error. The team with the car code " + carCodeTeam + " was not found.");
        }

        return raceFound;
    }
}
