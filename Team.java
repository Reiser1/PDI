public class Team {
    private Driver[] drivers;
    private String name;
    private int driverNumbers;
    private String carCode;
    private double fastestTime;

    /**
     * Constructor for the Team class
     * @param name the name of the team
     * @param carCode the car code of the team
     * @param drivers the drivers in the team
     */
    public Team(String name){
        this.name = name;
        this.drivers = new Driver[2];
        this.drivers[0] = null;
        this.drivers[1] = null;
        this.driverNumbers = 0;
        this.carCode = "";    

    }

    /**
     * Adds the fastest lap time of the team
     * @param FastestLap the fastest lap time of the team
     */
    public void addFastestTime(double FastestLap){
        this.fastestTime = FastestLap;
    }

    /**
     * Returns the name of the team
     * @return the name of the team
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the drivers in the team
     * @return the drivers in the team
     */
    public Driver[] getDrivers() {
        return this.drivers;
    }

    /**
     * Adds a driver to the team
     * @param driver the driver to be added to the team
     */
    public void addDriver(Driver driver) {
        this.drivers[driverNumbers] = driver;
        driverNumbers += 1;
    }

    /**
     * Returns the number of drivers in the team
     * @return the number of drivers in the team
     */
    public int driverNumbers() {
        return this.driverNumbers;
    }

    /**
     * Adds the car code of the team
     * @param carCode the car code of the team
     */
    public void addcarCode(String carCode){
        this.carCode = carCode;
    }

    /**
     * Prints the team information
     */
    public void showInfo(){
        for (int ii = 0; ii < this.driverNumbers; ii++) {
            System.out.println("Team Name: " + name);
            System.out.println("Car Code: " + carCode);
            System.out.println();
            drivers[ii].showInfo();
        }
    }

    /**
     * Prints out team name
     */
    public void showInfoTeam(){
        System.out.println(name);
    }

    /**
     * Returns the team name
    */
    public String getTeamName() {
        return this.name;
    }

    /**
     * Returns car code of the team
     */
    public String toString() {
        return this.carCode;
    }

    /**
     * Returns the fastest lap time of the team
     */
    public double getFastestLap() {
        return this.fastestTime;
    }
}
