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

    // ---------------- loading

    public int loadRidersFromCSV(String ridersFilePath) {
        return 0;
    }

    public int loadDriversFromCSV(String driversFilePath) {
        return 0;
    }

    public int loadRidesFromCSV(String ridesFilePath) {
        return 0;
    }

    // ---------------- riders

    public boolean addRider(IRider rider) {
        return false;
    }

    public IRider searchRiderById(int riderId) {
        return null;
    }

    public LinkedList<IRider> searchRidersByName(String fullName) {
        return new LinkedList<IRider>();
    }

    public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
        return new LinkedList<IRider>();
    }

    public LinkedList<IRider> getAllRiders() {
        return new LinkedList<IRider>();
    }

    public boolean removeRider(int riderId) {
        return false;
    }

    // ---------------- drivers

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

    // ---------------- rides

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

    // ---------------- tree statistics

    public int nbKeyCompRiderById(int riderId) {
        return 0;
    }

    public int indexSize(IndexName index) {
        return 0;
    }

    public int indexHeight(IndexName index) {
        return 0;
    }
}
