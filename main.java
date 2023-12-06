import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppClient {
    private Scanner scan;
    private ArrayList<Appliance> appliances = new ArrayList<>();

    public void readAppFile(String file) {
        try {
            File myFile = new File(file);
            scan = new Scanner(myFile);

            while (scan.hasNextLine()) {
                String[] applianceDetails = scan.nextLine().split(",");
                int locationID = Integer.parseInt(applianceDetails[0]);
                String appName = applianceDetails[1];
                int onPower = Integer.parseInt(applianceDetails[2]);
                float probOn = Float.parseFloat(applianceDetails[3]);
                String appType = applianceDetails[4];
                int lowPower = Integer.parseInt(applianceDetails[5]);

                Appliance aAppl = new Appliance(locationID, appName, onPower, probOn, appType, lowPower);
                appliances.add(aAppl);
            }
            scan.close();
        } catch (IOException ioe) {
            System.out.println("The file can not be read");
        }
    }

    public void addAppliance() {
        // Implement logic to add an appliance
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

        Appliance newAppliance = new Appliance(locationID, appName, onPower, probOn, isSmart, probSmart);
        appliances.add(newAppliance);
        System.out.println("Appliance added successfully.");
    }

    public void deleteAppliance() {
        // Implement logic to delete an appliance
        System.out.println("Enter the name of the appliance to delete:");
        String applianceName = scan.nextLine();

        appliances.removeIf(appliance -> appliance.getName().equals(applianceName));
        System.out.println("Appliance deleted successfully.");
    }

    public void listAppliances() {
        // Implement logic to list all appliances
        for (Appliance appliance : appliances) {
            System.out.println(appliance.toString());
        }
    }

    public void startSimulation() {
        // Placeholder for simulation logic
        System.out.println("Simulation is starting. Implement your simulation logic here.");
    }

    public static void main(String[] args) {
        AppClient app = new AppClient();
        app.scan = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("Type \"A\" Add an appliance");
            System.out.println("Type \"D\" Delete an appliance");
            System.out.println("Type \"L\" List the appliances");
            System.out.println("Type \"F\" Read Appliances from a file");
            System.out.println("Type \"S\" To Start the simulation");
            System.out.println("Type \"Q\" Quit the program");

            String option1 = app.scan.nextLine();

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
                    app.readAppFile("your_file_path"); // Replace with your actual file path
                    break;
                case "S":
                    app.startSimulation();
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
