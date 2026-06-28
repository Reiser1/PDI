import java.util.*;

public class AssignmentProgram1{
    public void run(Scanner scanner) {

        System.out.print("Welcome to the FIA F1 Data Entry Program.\nHow many F1 Teams are there? ");
        int numTeams = scanner.nextInt();
        scanner.nextLine();

        /**
         * Create an array of Team objects to store the teams
         */
        Team[] teams = new Team[10];
        teams[0] = new Team("Red Bull Racing");
        teams[1] = new Team("Ferrari");        
        teams[2] = new Team("McLaren");        
        teams[3] = new Team("Mercedes");        
        teams[4] = new Team("Aston Martin");        
        teams[5] = new Team("RB");    
        teams[6] = new Team("Haas F1 Team");    
        teams[7] = new Team("Williams");    
        teams[8] = new Team("Kick Sauber");    
        teams[9] = new Team("Alpine");   

        enterTeamData(scanner, teams, numTeams);
        moreData(scanner, teams);

    scanner.close();
    }

    /**
     * Method to enter team data
    */
    private void enterTeamData(Scanner scanner, Team[] teams, int numTeams){
        String teamName, carCode, driverName, grandPrix, position, time;
        Logic logicAnalyser = new Logic();

        /**
         * Iterate over the number of teams the user wants to enter
        */
        for (int i = 0; i < numTeams; i++) {
            boolean error = true;
            while (error) {
                System.out.print("Team Name: ");
                teamName = scanner.nextLine();
                
                System.out.print("Car Code: ");
                carCode = scanner.nextLine();

                System.out.print("Driver Name: ");
                driverName = scanner.nextLine();

                System.out.print("Grand Prix: ");
                grandPrix = scanner.nextLine();

                System.out.print("Position Finished: ");
                position = scanner.nextLine();

                System.out.print("Fastest Lap: ");
                time = scanner.nextLine();
                
                error = logicAnalyser.userInputerCheck(teams, teamName, carCode, driverName, grandPrix, position, time);        // Check if the user input is valid
            }
        }
    }

    /**
     * Method to enter more team data
    */
    private void moreData(Scanner scanner, Team[] teams){
        System.out.print("Would you like to enter more data (Y or N)? ");
        char choice = scanner.next().charAt(0); 
        if (choice == 'Y') {
            System.out.print("How many more teams would you like to add? ");
            int extraTeams = scanner.nextInt();
            scanner.nextLine();
            enterTeamData(scanner, teams, extraTeams);
            printData(scanner, teams);
            }
        else if (choice == 'N'){
            printData(scanner, teams);
        }
    }

    /**
     * Method to print the data
     */
    private void printData(Scanner scanner, Team[] teams){
        System.out.println("The current data looks like this:");
        System.out.println();
        /**
         * Iterate over each team and print the team information
         */
        for (int i = 0; i < teams.length; i++) {
            Team team = teams[i];
            if (team != null && team.driverNumbers() > 0) {
                team.showInfo();
                System.out.println("\n");
            }
        }
        CSVWriter csvWrite = new CSVWriter();
        csvWrite.saveData(scanner, teams);
    }
}
