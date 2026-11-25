import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RideSharingSystem implements IRideSharingSystem {
    // riders indexes
    private IBST<Integer, IRider> ridersById;
    private IBST<String, IBST<Integer, IRider>> ridersByName;
    private IBST<String, IBST<Integer, IRider>> ridersByHomeCity;

    // drivers indexes
    private IBST<Integer, IDriver> driversById;
    private IBST<String, IDriver> driversByPlate;
    private IBST<VehicleType, IBST<Integer, IDriver>> driversByVehicleType;

    // rides indexes
    private IBST<Integer, IRide> ridesById;
    private IBST<String, IBST<Integer, IRide>> ridesByPickupLocation;
    private IBST<IDateTime, IBST<Integer, IRide>> ridesByPickupTime;

    public RideSharingSystem() {
        ridersById = new BST<Integer, IRider>();
        ridersByName = new BST<String, IBST<Integer, IRider>>();
        ridersByHomeCity = new BST<String, IBST<Integer, IRider>>();

        driversById = new BST<Integer, IDriver>();
        driversByPlate = new BST<String, IDriver>();
        driversByVehicleType = new BST<VehicleType, IBST<Integer, IDriver>>();

        ridesById = new BST<Integer, IRide>();
        ridesByPickupLocation = new BST<String, IBST<Integer, IRide>>();
        ridesByPickupTime = new BST<IDateTime, IBST<Integer, IRide>>();
    }

    // helper methods for the indexes that have a BST inside a BST

    // add the object to the inner BST of this key, if the key is new make a new inner BST
    private <K extends Comparable<K>, T> void addToIndex(IBST<K, IBST<Integer, T>> index, K key, int id, T obj) {
        if (index.findKey(key)) {
            index.retrieve().insert(id, obj);
        }
        else {
            IBST<Integer, T> inner = new BST<Integer, T>();
            inner.insert(id, obj);
            index.insert(key, inner);
        }
    }

    // remove the object from the inner BST, if it becomes empty remove the key too
    private <K extends Comparable<K>, T> void removeFromIndex(IBST<K, IBST<Integer, T>> index, K key, int id) {
        if (index.findKey(key)) {
            IBST<Integer, T> inner = index.retrieve();
            inner.removeKey(id);
            if (inner.empty())
                index.removeKey(key);
        }
    }

    // return the objects of this key sorted by ID, or an empty list
    private <K extends Comparable<K>, T> LinkedList<T> searchIndex(IBST<K, IBST<Integer, T>> index, K key) {
        if (key != null && index.findKey(key))
            return index.retrieve().inOrder();
        return new LinkedList<T>();
    }

    // loading the files

    // each line: riderId,name,email,phoneNumber,homeCity
    public int loadRidersFromCSV(String ridersFilePath) {
        int count = 0;
        try {
            Scanner file = new Scanner(new File(ridersFilePath));
            while (file.hasNextLine()) {
                String[] parts = file.nextLine().split(",");
                if (parts.length < 5)
                    continue; // empty or wrong line
                try {
                    int id = Integer.parseInt(parts[0]);
                    if (addRider(new Rider(id, parts[1], parts[2], parts[3], parts[4])))
                        count++;
                } catch (IllegalArgumentException e) {
                    // wrong id or phone, skip the line
                }
            }
            file.close();
            return count;
        } catch (FileNotFoundException e) {
            return -1;
        }
    }

    public int loadDriversFromCSV(String driversFilePath) {
        return 0;
    }

    public int loadRidesFromCSV(String ridesFilePath) {
        return 0;
    }

    // riders

    public boolean addRider(IRider rider) {
        if (rider == null || rider.getName() == null || rider.getHomeCity() == null)
            return false;

        // check the id first so we don't add it to some indexes only
        if (ridersById.findKey(rider.getId()))
            return false;

        // add it to the three riders indexes
        ridersById.insert(rider.getId(), rider);
        addToIndex(ridersByName, rider.getName(), rider.getId(), rider);
        addToIndex(ridersByHomeCity, rider.getHomeCity(), rider.getId(), rider);
        return true;
    }

    public IRider searchRiderById(int riderId) {
        if (ridersById.findKey(riderId))
            return ridersById.retrieve();
        return null;
    }

    public LinkedList<IRider> searchRidersByName(String fullName) {
        return searchIndex(ridersByName, fullName);
    }

    public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
        return searchIndex(ridersByHomeCity, homeCity);
    }

    public LinkedList<IRider> getAllRiders() {
        return ridersById.inOrder();
    }

    public boolean removeRider(int riderId) {
        return false;
    }

    // drivers

    public boolean addDriver(IDriver driver) {
        return false;
    }

    public IDriver searchDriverById(int driverId) {
        return null;
    }

    public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType) {
        return new LinkedList<IDriver>();
    }

    public LinkedList<IDriver> getAllDrivers() {
        return new LinkedList<IDriver>();
    }

    public boolean removeDriver(int driverId) {
        return false;
    }

    // rides

    public int schedulePrivateRide(String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, int riderId, int driverId) {
        return -1;
    }

    public int scheduleSharedRide(String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, int[] riderIds, int driverId) {
        return -1;
    }

    public boolean cancelRide(int rideId) {
        return false;
    }

    public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
        return new LinkedList<IRide>();
    }

    public LinkedList<IRide> searchRidesByRiderName(String riderName) {
        return new LinkedList<IRide>();
    }

    public LinkedList<IRider> getSharedRideParticipants(int rideId) {
        return new LinkedList<IRider>();
    }

    public LinkedList<IRide> getAllRidesAlphabetically() {
        return new LinkedList<IRide>();
    }

    public LinkedList<IRide> searchRidesByPickupTimeRange(IDateTime from, IDateTime to) {
        return new LinkedList<IRide>();
    }

    public LinkedList<IDriver> findAvailableDrivers(ITimeSlot timeSlot, VehicleType vehicleType) {
        return new LinkedList<IDriver>();
    }

    // tree statistics

    public int nbKeyCompRiderById(int riderId) {
        return ridersById.nbKeyComp(riderId);
    }

    public int indexSize(IndexName index) {
        return 0;
    }

    public int indexHeight(IndexName index) {
        return 0;
    }
}