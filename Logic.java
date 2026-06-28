public class Logic{
    /**
     * This method will check the user input and return a boolean value
     */
    public boolean userInputerCheck(Team[] teams, String teamName, String carCode, String driverName, String grandPrix, String position, String time){
        boolean error = false;
        try {
            inputCheck(teamName, carCode, driverName, grandPrix);
            int positionFinished = validPosition(position);
            double fastestLap = validTime(time);

            boolean informationAdded = addInformation(teams, teamName, carCode, driverName, grandPrix, positionFinished, fastestLap);
            error = incorrectTeamName(informationAdded);

        } catch (NumberFormatException e) { // Catching the exception when the user enters a string instead of a number
            System.out.println("Error. " + e);
            System.out.println("Re-enter the Data.");
            error = true; 

        } catch (ArrayIndexOutOfBoundsException exception) { // Catching the exception when the user enters more than 2 drivers for a team
            System.out.println("Error. Each team can only have 2 drivers");
            System.out.println("Re-enter the Data.");
            error = true; 
        } catch (Exception exception) { // Catching the exception when the user enters an empty string
            System.out.println("Error. " + exception);
            System.out.println("Re-enter the Data.");
            error = true; 
        }return error;
    }
    
    /**
     * This method will check if the driver exists and return a boolean value
     */
    private boolean driverExists(String driverName, Team[] teams){
        boolean driverFound = false;
        // This loop iterates over the teams to find the driver
        for (int i = 0; i < teams.length; i++) {
            // This loop iterates over the drivers of the team to find the driver
            for (int driverNumber = 0; driverNumber < teams[i].driverNumbers(); driverNumber++) {
                if (driverName.equals(teams[i].getDrivers()[driverNumber].getName())) {
                    driverFound = true;
                }
            }
        }
        return driverFound;
    }

    /**
     * This method will find the driver and return the driver object
     */
    private Driver findDriver(String driverName, Team[] teams) {
        Driver driver = null;
        for (int i = 0; i < teams.length; i++) {
            for (int driverNumber = 0; driverNumber < teams[i].driverNumbers(); driverNumber++) {
                if (driverName.equals(teams[i].getDrivers()[driverNumber].getName())) {
                    driver = teams[i].getDrivers()[driverNumber];
                }
            }
        }
        return driver;
    }

    /**
     * This method will check if the user input is empty and return a boolean value
     */
    private void inputCheck(String teamName, String carCode, String driverName, String grandPrix) throws Exception {
        if (teamName.isEmpty() || carCode.isEmpty() || driverName.isEmpty() || grandPrix.isEmpty()) {
            throw new Exception("EMPTY");
        }
    }

    /**
     * This method will check if the user input is a number and return an integer
     */
    private int validPosition(String position) throws Exception {
        int positionFinished = Integer.parseInt(position);
        return positionFinished;
    }

    /**
     * This method will check if the user input is a number and return a double
     */
    private double validTime(String time) throws Exception {
        double fastestLap = Double.parseDouble(time);
        // This loop checks if the user input is a positive number
        if (fastestLap <= 0) {
            throw new Exception("Position Finished and Fastest Lap must be positive numbers.");
        }

        return fastestLap;
    }

    /**
     * This method will add the information to the team and return a boolean value
     */
    private boolean addInformation(Team[] teams, String teamName, String carCode, String driverName, String grandPrix, int positionFinished, double fastestLap) {
        boolean teamInfoAdded = false;

        // This loop iterates over the teams to find the team
        for (int i = 0; i < teams.length; i++) {
            // This if statement checks if the team name is correct
            if ((teams[i].getName()).equals(teamName)) {
                GrandPrix race = new GrandPrix(grandPrix, positionFinished, fastestLap);
                Driver driver;
                // This if statement checks if the driver exists if it does, it adds the the data to the driver already created
                if (driverExists(driverName, teams)) {
                    driver = findDriver(driverName, teams);
                    driver.addRace(race);
                }   
                // This else statement creates a new driver and adds the data to the driver
                else {
                    driver = new Driver(driverName);
                    driver.addRace(race);
                    driver.setTeam(teams[i]);
                    teams[i].addDriver(driver);
                    teams[i].addcarCode(carCode);
                }
        
                System.out.println();
                System.out.println("INFORMATION ADDED");
                System.out.println();
                teamInfoAdded = true;
            }
        }
        return teamInfoAdded;
    }

    /**
     * This method will check if the team name is correct and return a boolean value
     */
    private boolean incorrectTeamName(boolean teamInfoAdded) {
        boolean error = false;
        // This if statement checks if the team name is incorrect
        if (!teamInfoAdded){
            System.out.println();
            System.out.println("INFORMATION NOT ADDED. INCORRECT TEAM NAME. RE-ENTER TEAM NAME.");
            System.out.println();
            error = true; 
        }
        return error;
    }    
}