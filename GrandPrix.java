public class GrandPrix {
    private int position;
    private double time;
    private String name;
    
    /**
     * Define a constructor for the GrandPrix class that takes a String, an integer, and a double as parameters
     */
    public GrandPrix(String name, int position, double time) {
        this.position = position; 
        this.time = time; 
        this.name = name;
    }


    /**
     * Define a method that returns the position the driver finished in
     * @return the position the driver finished in
     */
    public double getFastestLap(){
        return this.time;
    }

    /**
     * Define a method that prints the Grand Prix's information
     */
    public void showInfo() {
        System.out.println("Grand Prix: " + name);
        System.out.println("Position Finished: " + position);
        System.out.println("Fastest Lap: " + time);
        System.out.println();
    }

    /**
    * Override the toString method to return a string representation of the Grand Prix
    * @return a string representation of the Grand Prix
     */
    public String toString() {
        return this.name + ", " + this.position + ", " + this.time;
    }

    /**
     * Define a method that returns the name of the Grand Prix
     * @return the name of the Grand Prix
     */
    public String getName(){
        return this.name;
    }
}