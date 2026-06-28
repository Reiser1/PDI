public class Driver {
    private String name;
    private GrandPrix[] grandPrixs;
    private int prixsPerformed;
    private Team team;
    private String teamN;
    private String carCode;
    private double fastestLap;

    /**
     * Constructor for the Driver class
     * @param name The name of the driver
     */
    public Driver(String name){
        this.name = name;
        grandPrixs = new GrandPrix[24];
        prixsPerformed = 0;
    }

    /**
     * This method adds the team name of the driver as a String
    */
    public void addTeamN(String team1){
        this.teamN = team1;
    }

    /**
     * This method sets the team of the driver
    */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * This method adds the car code of the driver
    */
    public void addCarCode(String carCode){
        this.carCode = carCode;
    }

    /**
     * This method adds a new GrandPrix race to the list of races performed by the team.
     * The race is added to the next available position in the grandPrixs array,
     * and the count of performed races (prixsPerformed) is incremented by one.
     * @param race The GrandPrix race to be added to the list of races performed by the team.
     */
    public void addRace(GrandPrix race) {
        grandPrixs[prixsPerformed] = race;
        prixsPerformed += 1;
    }

    /**
     * This method adds the fastest lap time of the driver
    */
    public void addFastestLap(double FastestLap){
        this.fastestLap = FastestLap;

    }

    /**
     * This method returns the name of the driver
     */
    public String getName() {
        return this.name;
    }

    /**
     * This method returns the fastest lap time of the driver
     */
    public double getFastestLap(){
        return this.fastestLap;
    }

    /**
     * This method returns the team of the driver
    */
    public Team getTeam(){
        return this.team;
    }

    /**
     * This method returns the team name of the driver as a String
    */
    public String getTeamName(){
        return this.teamN;
    }

    /**
     * This method returns the car code of the driver as a String
    */
    public String getCarCode(){
        return this.carCode;
    }

    /**
     * This method returns the number of Grand Prix performed
     */
    public int getRacesRaced() {
        return this.prixsPerformed;
    }

    /**
     * This method returns the Grand Prixs performed by the driver
     */
    public GrandPrix[] getGrandPrixs() {
        return this.grandPrixs;
    }

    /**
     * This method prints the information of the driver
     */
    public void showInfo() {
        System.out.println("Driver Name: " + name);
        for (int ii = 0; ii < prixsPerformed; ii++) {
            grandPrixs[ii].showInfo();
        }
    }

    @Override
    /**
     * This method returns the name of the driver as a String
     */
    public String toString() {
        return this.name;
    }
}