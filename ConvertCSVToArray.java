import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConvertCSVToArray {
    private String fileName;

    /**
     * Constructor for the ConvertCSVToArray class
     */
    public ConvertCSVToArray(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Reads the CSV file and converts it into an array of Driver objects
     */
    public Driver[] readCSV() {
        FileInputStream fileStream = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        int lineNum;
        String line;
        @SuppressWarnings("unused")
        String input;
        int count = 0;
        Driver[] driverArray = null;
        
        /**
         * Try to read the file and convert it into an array of Driver objects
         */
        try{
            fileStream = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            line = bufRdr.readLine();
            lineNum = 0;
    
            // Count the number of lines in the file
            while((input = bufRdr.readLine()) != null)
            {
                count++;
            }

            // Create an array of Driver objects based on the number of lines in the file
            driverArray = new Driver[count];
            
            fileStream.getChannel().position(0);
            rdr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(rdr);
            bufRdr.readLine();

            // Process each line in the file and convert it into a Driver object
            while ((line = bufRdr.readLine()) != null) {
                driverArray[lineNum] = processLine(line);
                lineNum++;
            }
            fileStream.close();

            return driverArray;

        // Catch any exceptions that occur during file processing
        }catch(IOException errorDetails){
            if(fileStream != null) 
            {
                try 
                {
                    fileStream.close();
                }
                catch(IOException ex2) 
                { }
            }
            System.out.println("Error in file processing: " + errorDetails.getMessage());
        }
        return driverArray;
    }
    
    /**
     * Processes a line from the CSV file and converts it into a Driver object
     */
    private Driver processLine(String csvRow)
    {
        String[] splitLine;
        splitLine = csvRow.split(",");
        @SuppressWarnings("unused")
        int lineLength = splitLine.length;

        // Extract the data from the CSV line
        String carCode = splitLine[1].strip();
        String raceName = splitLine[3].strip();
        Integer positionFinished = Integer.parseInt(splitLine[4].strip());
        Double fastestLap = Double.parseDouble(splitLine[5].strip());
        String team1 = splitLine[0].strip();

        // Create the Driver, Team, and GrandPrix objects
        Team team = new Team(splitLine[0].strip());
        Driver driver = new Driver(splitLine[2].strip());
        GrandPrix race = new GrandPrix(raceName, positionFinished, fastestLap);

        // Add the Driver, Team, and GrandPrix objects to each other
        driver.addRace(race);
        driver.setTeam(team);
        driver.addTeamN(team1);
        driver.addCarCode(carCode);
        team.addDriver(driver);
        team.addcarCode(carCode);

        // Return the Driver object
        return driver;
    }
}