import java.util.Scanner;

public class Print {

    /**
     * This method will print the teams that raced in the Grand Prix and return a boolean value
     */
    public boolean printRacedTeams(Scanner scanner, Driver[] driverArray, String raceName) {
        boolean teamsInRace = false;

        // This loop iterates over the drivers to find the teams that raced in the Grand Prix
        for (int i = 0; i < driverArray.length; i++) {
            GrandPrix[] races = driverArray[i].getGrandPrixs();
            // This loop iterates over the Grand Prixs of the driver to find the teams that raced in the Grand Prix
            for (int j = 0; j < driverArray[i].getRacesRaced(); j++) {
                if (races[j].getName().equals(raceName)) {
                    teamsInRace = true;
                    System.out.println(driverArray[i].getTeamName() + " raced " + raceName + " Grand Prix with " + driverArray[i]);
                    System.out.println("");
                }
            }
        }
        return teamsInRace;
    }

    /**
     * This method prints information about the teams that have not participated in a specific race.
     */
    public void printNotRacedTeams(Scanner scanner, Driver[] driverArray, Team[] teams, String raceName){
        boolean raced;
    
        // This loop iterates over the teams to find the teams that have not raced in the Grand Prix
        for (int i = 0; i < teams.length; i++) {
            Team team = teams[i];
            raced = false;
            for (int j = 0; j < driverArray.length; j++) {
                Driver driver = driverArray[j];
                GrandPrix[] races = driver.getGrandPrixs();
                // This loop iterates over the Grand Prixs of the driver to find the teams that have not raced in the Grand Prix
                for (int k = 0; k < driver.getRacesRaced(); k++) {
                    if (races[k].getName().equals(raceName) && driver.getTeamName().equals(team.getName())) {
                        raced = true;
                        break;
                    }
                }
                if (raced) {
                    break;
                }
            }
            if (!raced) {
                team.showInfoTeam();
                System.out.println("");
            }
        }
    }

    /**
     * This method will print the fastest driver for a single team
     */
    public void printSingleTeamAnalysis(Scanner scanner, Driver[] driverArray, String driver1, String driver2, String raceName, String carCodeTeam, double fastestLap1, double fastestLap2){
        // This loop iterates over the drivers to find the fastest driver for the team
        for (int f = 0; f < driverArray.length; f++) {
            Driver driver = driverArray[f];
            // This if statement checks if the driver is part of the team
            if (driver.getCarCode().equals(carCodeTeam)){
                if (fastestLap1 == 205.5 || driver1 == ""){
                    System.out.println();
                    System.out.println(driver2 + " was the fastest driver for " + driverArray[f].getTeamName() + " in the " + raceName + " Grand Prix.");
                    System.out.println(driver2 + " - " + fastestLap2);
                    break;
                }
                else if (fastestLap2 == 205.5 || driver2 == ""){
                    System.out.println();
                    System.out.println(driver1 + " was the fastest driver for " + driverArray[f].getTeamName() + " in the " + raceName + " Grand Prix.");
                    System.out.println(driver1 + " - " + fastestLap1);
                    break;
                }
                else if (fastestLap1 == fastestLap2){
                    System.out.println(driver1 + " and " + driver2 + " had the the same fastest lap time " + " in the " + raceName + " Grand Prix.");
                }
                else if (fastestLap1 > fastestLap2){
                    System.out.println("Here is the list of drivers for " + driver.getTeamName() + " from fastest to slowest.");
                    System.out.println(driver2 + " - " + fastestLap2 + " seconds\n"+ driver1 + " - " + fastestLap1 + " seconds");
                    break;
                }
                else if (fastestLap1 < fastestLap2){
                    System.out.println("Here is the list of drivers for " + driver.getTeamName() + " from fastest to slowest.");
                    System.out.println(driver1 + " - " + fastestLap1 + " seconds\n"+ driver2 + " - " + fastestLap2 + " seconds");
                    break;
                }
            }
        }
    }
}
