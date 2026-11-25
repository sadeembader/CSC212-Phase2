
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RideSharingSystem system = new RideSharingSystem();

        // load the three files before showing the menu
        int ridersLoaded = system.loadRidersFromCSV("riders_100.csv");
        int driversLoaded = system.loadDriversFromCSV("drivers_30.csv");
        int ridesLoaded = system.loadRidesFromCSV("rides_40.csv");

        System.out.println("Riders loaded: " + ridersLoaded);
        System.out.println("Drivers loaded: " + driversLoaded);
        System.out.println("Rides loaded: " + ridesLoaded);

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
                choice = -1; // not a number, goes to "Invalid choice"
            }
            input.nextLine();

            switch (choice) {
                case 1: // list all riders
                    LinkedList<IRider> allRiders = system.getAllRiders();
                    if (allRiders.empty()) {
                        System.out.println("Rider list is empty.");
                    } else {
                        System.out.println("--- All Riders ---");
                        allRiders.findFirst();
                        while (true) {
                            System.out.println(allRiders.retrieve().toString());
                            if (allRiders.last())
                                break;
                            allRiders.findNext();
                        }
                    }
                    break;

                case 2: // search rider by ID
                    try {
                        System.out.print("Enter rider ID: ");
                        int searchId = input.nextInt();
                        input.nextLine();

                        IRider found = system.searchRiderById(searchId);
                        if (found == null)
                            System.out.println("No rider found with this ID.");
                        else
                            System.out.println(found.toString());

                        System.out.println("Number of key comparisons: " + system.nbKeyCompRiderById(searchId));
                    } catch (Exception e) {
                        System.out.println("Error: Please enter a number.");
                        input.nextLine();
                    }
                    break;

                case 3: // search riders by home city
                    System.out.print("Enter city name to search: ");
                    String city = input.nextLine();
                    LinkedList<IRider> cityResults = system.searchRidersByHomeCity(city);

                    if (cityResults.empty()) {
                        System.out.println("No riders found in this city.");
                    } else {
                        System.out.println("--- Search Results ---");
                        cityResults.findFirst();
                        while (true) {
                            System.out.println(cityResults.retrieve().toString());
                            if (cityResults.last())
                                break;
                            cityResults.findNext();
                        }
                    }
                    break;
                case 4: // list all rides
                    break;
                case 5: // schedule of a rider or a driver
                    break;
                case 6: // size and height of the indexes
                    break;
                case 7: // add a rider
                    try {
                        System.out.print("Enter ID: ");
                        int id = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter Full Name: ");
                        String fullName = input.nextLine();

                        System.out.print("Enter Email: ");
                        String email = input.nextLine();

                        System.out.print("Enter Phone Number (10 digits): ");
                        String phone = input.nextLine();

                        System.out.print("Enter Home City: ");
                        String homeCity = input.nextLine();

                        Rider newRider = new Rider(id, fullName, email, phone, homeCity);
                        if (system.addRider(newRider))
                            System.out.println("Rider added successfully!");
                        else
                            System.out.println("Error: A rider with the same ID already exists.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Input Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: Please enter the data in the correct format.");
                        input.nextLine();
                    }
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