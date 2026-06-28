import java.util.*;


public class AssignmentProgram2{
    
    /**
     * Main method that runs the program
     */
    public void run(Scanner scanner){
        String csvFile;
        Print print = new Print();
        boolean fileFound = false;

        // Array of teams
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

        /**
         * Do while loop that asks the user for the name of the file containing the data
         */
        do{
            System.out.println();
            System.out.print("What is the name of the file containing the data: ");
            csvFile = scanner.nextLine();
            ConvertCSVToArray csv = new ConvertCSVToArray(csvFile + ".csv");
            Driver[] drivers = csv.readCSV();
            
            // If the file is not found, the user is asked to try again
            if (drivers == null){
                System.out.println();
                System.out.println("File not found. Please try again.");
                fileFound = true;
            }
            else{
                selectAnalysisType(drivers, teams, print);
            }
        }while(fileFound);


        scanner.close();
    }


    /**
     * Method that asks the user if they would like to analyze all teams or a single team
     */
    private void selectAnalysisType(Driver[] driverArray, Team[] teams, Print print){
        Scanner scanner = new Scanner(System.in);
        boolean incorrectInput = true;
        int analysis;

        do {
            System.out.println();
            System.out.print("Would you like:\n> (1) All Team analysis \n> (2) Single Team analysis\n>  ");
            analysis = scanner.nextInt();
            if (analysis == 1){
                fullTeamAnalysis(scanner, driverArray, teams, print);
                incorrectInput = false;
            }
            else if (analysis == 2){
                singleTeamAnalysis(scanner, driverArray, print);
                incorrectInput = false;
            }
            else{
                System.out.println();
                System.out.print("Invalid Option. Choose either 1 or 2.");
                System.out.println();
            }
        } while(incorrectInput);
        scanner.close();
    }

    /**
     * Method that analyzes all teams
     */
    private void fullTeamAnalysis(Scanner scanner, Driver[] driverArray, Team[] teams, Print print){
        String raceName;
        boolean teamsInGrandPrix = true;
        boolean teamsRaced;

        scanner.nextLine();
        // Do while loop that asks the user for the name of the Grand Prix they would like to analyze
        do{
            System.out.println();
            System.out.print("What Grand Prix would you like to analyze data for? ");
            raceName = scanner.nextLine();
            System.out.println();
    
            System.out.println("The following teams have raced in the " + raceName + " Grand Prix.");
            System.out.println();
    
            teamsRaced = print.printRacedTeams(scanner, driverArray, raceName);
    
            // If no teams have raced in the Grand Prix, the user is asked to select another Grand Prix
            if (teamsRaced == false) {
                System.out.println("No teams raced in the " + raceName + " Grand Prix.");
                System.out.println();
                System.out.print("Select another Grand Prix.");
                System.out.println();
            }
            // If teams have raced in the Grand Prix, the user is asked if they would like to sort the data
            else if (teamsRaced == true){
                System.out.println();
                System.out.println("The following teams haven't raced in the " + raceName + " Grand Prix.");
                System.out.println();
                print.printNotRacedTeams(scanner,driverArray, teams, raceName);
                options(scanner, driverArray, raceName, print);
                teamsInGrandPrix = false;
            }
        }while(teamsInGrandPrix);

    }

    /**
     * Method that asks the user if they would like to sort the data
     */
    private void options(Scanner scanner, Driver[] driverArray, String raceName, Print print){
        SortingData SortingData = new SortingData();
        int choice;
        boolean incorrectChoice;
        incorrectChoice = true;

        do {
            System.out.println();
            System.out.println("Would you like to sort the F1 Data:");
            System.out.println("> 1: According to which team was fastest in descending order.");
            System.out.println("> 2: According to which driver was fastest in descending order.");
            System.out.print("> 3: According to which driver was fastest in ascending order.\n> ");
            choice = scanner.nextInt();
            System.out.println();

            if (choice == 1){
                DataAnalysis.allTeamsAnalysis(scanner, driverArray, raceName);
                incorrectChoice = false;
            }
            else if (choice == 2 || choice == 3){
                SortingData.driverSorting(scanner, driverArray, raceName, choice);
                incorrectChoice = false;
            }
            else{
                System.out.println("Error. Incorrect input. Please enter a number between 1 and 3.");
            }   
        }while(incorrectChoice);
    }

    /**
     * Method that analyzes a single team based on the user's input
     */
    private void singleTeamAnalysis(Scanner scanner, Driver[] driverArray, Print print){
        String carCodeTeam;
        String raceName;
        boolean teamCodeCorrect = true;
        DataAnalysis dataAnalysis = new DataAnalysis();

        scanner.nextLine();
        // Do while loop that asks the user for the Car Code of the team and the Grand Prix they would like to analyze
        do{
            System.out.println();
            System.out.print("What is the Car Code of the team you would like to analyze? ");
            carCodeTeam = scanner.nextLine();

            System.out.println();
            System.out.print("What Grand Prix would you like to analyze data for? ");
            raceName = scanner.nextLine();

            teamCodeCorrect = dataAnalysis.findingTeamRace(scanner, driverArray, carCodeTeam, raceName, print);
            
        }while(teamCodeCorrect);
    }
}