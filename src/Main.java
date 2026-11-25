import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RideSharingSystem system = new RideSharingSystem();

        // load the files in this order
        System.out.println("Riders loaded: " + system.loadRidersFromCSV("riders_100.csv"));
        System.out.println("Drivers loaded: " + system.loadDriversFromCSV("drivers_30.csv"));
        System.out.println("Rides loaded: " + system.loadRidesFromCSV("rides_40.csv"));

        int choice;
        do {
            System.out.println("\n--- Ride-Sharing System Menu ---");
            System.out.println("1. List all riders");
            System.out.println("2. Search for a rider by ID");
            System.out.println("3. Search for riders by home city");
            System.out.println("4. List all rides alphabetically by pickup location");
            System.out.println("5. Show the schedule of a rider or a driver");
            System.out.println("6. Show the size and height of each index");
            System.out.println("7. Add a rider");
            System.out.println("8. List the rides whose pickup time falls in a given range");
            System.out.println("9. Find the available drivers of a given vehicle type for a given time slot");
            System.out.println("10. Add a ride (private or shared)");
            System.out.println("11. Cancel a ride");
            System.out.println("12. Remove a rider");
            System.out.println("0. Exit the program");
            System.out.print("Enter your choice: ");

            try {
                choice = input.nextInt();
            } catch (Exception e) {
                choice = -1; // not a number
            }
            input.nextLine();

            switch (choice) {
                case 1: // list all riders
                    break;
                case 2: // search rider by ID
                    break;
                case 3: // search riders by home city
                    break;
                case 4: // list all rides
                    break;
                case 5: // schedule of a rider or a driver
                    break;
                case 6: // size and height of the indexes
                    break;
                case 7: // add a rider
                    break;
                case 8: // rides in a pickup time range
                    break;
                case 9: // available drivers
                    break;
                case 10: // add a ride
                    break;
                case 11: // cancel a ride
                    break;
                case 12: // remove a rider
                    break;
                case 0:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);

        input.close();
    }
}
