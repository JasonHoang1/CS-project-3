import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppClient {
    private Scanner scan;
    private ArrayList<Appliance> appliances = new ArrayList<>();

    // Method to read appliance details from a file
    public void readAppFile(String file) {
        try {
            File myFile = new File(file);
            scan = new Scanner(myFile);

            while (scan.hasNextLine()) {
                // Split the line into individual details using ","
                String[] applianceDetails = scan.nextLine().split(",");
                int locationID = Integer.parseInt(applianceDetails[0]);
                String appName = applianceDetails[1];
                int onPower = Integer.parseInt(applianceDetails[2]);
                float probOn = Float.parseFloat(applianceDetails[3]);
                String appType = applianceDetails[4];
                int lowPower = Integer.parseInt(applianceDetails[5]);

                // Create a new Appliance object and add it to the list
                Appliance aAppl = new Appliance(locationID, appName, onPower, probOn, appType, lowPower);
                appliances.add(aAppl);
            }
            scan.close();
        } catch (IOException ioe) {
            System.out.println("The file can not be read");
        }
    }

    // Method to add a new appliance
    public void addAppliance() {
        System.out.println("Enter location ID:");
        int locationID = Integer.parseInt(scan.nextLine());

        System.out.println("Enter appliance name:");
        String appName = scan.nextLine();

        System.out.println("Enter on power:");
        int onPower = Integer.parseInt(scan.nextLine());

        System.out.println("Enter probability of staying on:");
        float probOn = Float.parseFloat(scan.nextLine());

        System.out.println("Enter smart (true/false):");
        boolean isSmart = Boolean.parseBoolean(scan.nextLine());

        System.out.println("Enter power reduction percent when changed to low status (floating point, e.g., 0.33 for 33% reduction):");
        float probSmart = isSmart ? Float.parseFloat(scan.nextLine()) : 0.0f;

        // Create a new Appliance object and add it to the list
        Appliance newAppliance = new Appliance(locationID, appName, onPower, probOn, isSmart, probSmart);
        appliances.add(newAppliance);
        System.out.println("Appliance added successfully.");
    }

    // Method to delete an existing appliance
    public void deleteAppliance() {
        System.out.println("Enter the name of the appliance to delete:");
        String applianceName = scan.nextLine();

        // Remove the appliance with the specified name from the list
        appliances.removeIf(appliance -> appliance.getName().equals(applianceName));
        System.out.println("Appliance deleted successfully.");
    }

    // Method to list all existing appliances
    public void listAppliances() {
        System.out.println("List of Appliances:");
        for (Appliance appliance : appliances) {
            System.out.println(appliance.toString());
        }
        System.out.println("End of List");
    }

    // Method to start the simulation
    public void startSimulation(double totalAllowedWattage, String filePath, int simulationLength) {
        // Placeholder for simulation logic
        System.out.println("Simulation is starting. Implement your simulation logic here.");
        System.out.println("Total Allowed Wattage: " + totalAllowedWattage);
        System.out.println("File Path: " + filePath);
        System.out.println("Simulation Length: " + simulationLength);
    }

    // Main method
    public static void main(String[] args) {
        AppClient app = new AppClient();
        app.scan = new Scanner(System.in);

        // Prompt for total allowed wattage
        System.out.println("Enter the total allowed wattage (power):");
        double totalAllowedWattage = 0.0;
        boolean validTotalWattage = false;
        while (!validTotalWattage) {
            try {
                totalAllowedWattage = Double.parseDouble(app.scan.nextLine());
                validTotalWattage = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for total allowed wattage.");
            }
        }

        // Prompt for comma-separated text file containing appliances
        System.out.println("Enter the path of the comma-separated text file containing the appliances:");
        String filePath = app.scan.nextLine();

        // Prompt for simulation length in timesteps
        System.out.println("Enter the simulation length in timesteps:");
        int simulationLength = 0;
        boolean validSimulationLength = false;
        while (!validSimulationLength) {
            try {
                simulationLength = Integer.parseInt(app.scan.nextLine());
                validSimulationLength = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for simulation length.");
            }
        }

        // Main program loop
        while (true) {
            // Display menu
            System.out.println("Select an option:");
            System.out.println("Type \"A\" Add an appliance");
            System.out.println("Type \"D\" Delete an appliance");
            System.out.println("Type \"L\" List the appliances");
            System.out.println("Type \"F\" Read Appliances from a file");
            System.out.println("Type \"S\" To Start the simulation");
            System.out.println("Type \"Q\" Quit the program");

            // Get user input
            String option1 = app.scan.nextLine();

            // Perform actions based on user input
            switch (option1.toUpperCase()) {
                case "A":
                    app.addAppliance();
                    break;
                case "D":
                    app.deleteAppliance();
                    break;
                case "L":
                    app.listAppliances();
                    break;
                case "F":
                    app.readAppFile(filePath);
                    break;
                case "S":
                    app.startSimulation(totalAllowedWattage, filePath, simulationLength);
                    break;
                case "Q":
                    app.scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
