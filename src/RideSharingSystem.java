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
   // Generate a unique ID for each successfully scheduled ride
   private int nextRideId = 1;

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
      // Validate the ride information
      if (pickupLocation == null || dropoffLocation == null ||
        timeSlot == null || timeSlot.getStart() == null || timeSlot.getEnd() == null ||
        timeSlot.getStart().compareTo(timeSlot.getEnd()) >= 0)
         return -1;
   
    // Check if the rider exists
      IRider rider = searchRiderById(riderId);
      if (rider == null)
         return -1;
   
    // Check if the driver exists
      IDriver driver = searchDriverById(driverId);
      if (driver == null)
         return -1;
   
    // Check for conflicts in both schedules
      if (rider.getSchedule().findKey(timeSlot) || driver.getSchedule().findKey(timeSlot))
         return -1;
   
    // Create the private ride
      int rideId = nextRideId;
   
      IRide newRide = new PrivateRide(rideId, pickupLocation, dropoffLocation, timeSlot, rider, driver);
   
    // Add the ride to the three ride indexes
      ridesById.insert(rideId, newRide);
   
      addToIndex(ridesByPickupLocation, pickupLocation, rideId, newRide);
   
      addToIndex(ridesByPickupTime,timeSlot.getStart(), rideId, newRide);
   
    // Add the ride to both schedules
      rider.getSchedule().insert(timeSlot, newRide);
      driver.getSchedule().insert(timeSlot, newRide);
   
    // Increment the ID after successful scheduling
      nextRideId++;
   
      return rideId;
   }
   public int scheduleSharedRide(String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, int[] riderIds, int driverId) {
      // Validate the ride information
      if (pickupLocation == null || dropoffLocation == null ||
        timeSlot == null || timeSlot.getStart() == null || timeSlot.getEnd() == null ||
        timeSlot.getStart().compareTo(timeSlot.getEnd()) >= 0 || riderIds == null || riderIds.length <2)
         return -1;
   
    // Check if the driver exists
      IDriver driver = searchDriverById(driverId);
      if (driver == null)
         return -1;
   
    // Check the driver's schedule
      if (driver.getSchedule().findKey(timeSlot))
         return -1;
   
    // Create the shared ride
      int rideId = nextRideId;
      SharedRide newRide = new SharedRide(rideId,pickupLocation, dropoffLocation, timeSlot, driver);
   
    // Validate and add all participants
      for (int i = 0; i < riderIds.length; i++) {
         IRider rider = searchRiderById(riderIds[i]);
      
         if (rider == null)
            return -1;
      
        // Check the rider's schedule
         if (rider.getSchedule().findKey(timeSlot))
            return -1;
      
        // Prevent duplicate participants
         if (!newRide.addParticipant(rider))
            return -1;
      }
   
    // Add the ride to the three ride indexes
      ridesById.insert(rideId, newRide);
   
      addToIndex(ridesByPickupLocation,pickupLocation, rideId, newRide);
   
      addToIndex(ridesByPickupTime,timeSlot.getStart(), rideId, newRide);
   
    // Add the ride to the driver's schedule
      driver.getSchedule().insert(timeSlot, newRide);
   
    // Add the ride to all riders' schedules
      LinkedList<IRider> participants = newRide.getParticipants();
   
      if (!participants.empty()) {
         participants.findFirst();
      
         while (!participants.last()) {
            participants.retrieve().getSchedule().insert(timeSlot, newRide);
         
            participants.findNext();
         }
      
         participants.retrieve().getSchedule().insert(timeSlot, newRide);
      }
   
    // Increment the ID after successful scheduling
      nextRideId++;
   
      return rideId;
   }


    // Cancel a ride and remove it from all indexes and schedules
   @Override
   public boolean cancelRide(int rideId) {
   
    // Check if the ride exists
      if (!ridesById.findKey(rideId))
         return false;
   
      IRide ride = ridesById.retrieve();
      ITimeSlot timeSlot = ride.getTimeSlot();
   
    // Remove the ride from the driver's schedule
      ride.getDriver().getSchedule().removeKey(timeSlot);
   
    // Remove the ride from the private rider's schedule
      if (ride instanceof IPrivateRide) {
         IRider rider = ((IPrivateRide) ride).getRider();
         rider.getSchedule().removeKey(timeSlot);
      }
      
      // Remove the ride from all shared riders' schedules
      else if (ride instanceof ISharedRide) {
         LinkedList<IRider> participants = ((ISharedRide) ride).getParticipants();
      
         if (!participants.empty()) {
            participants.findFirst();
         
            while (true) {
               participants.retrieve().getSchedule().removeKey(timeSlot);
            
               if (participants.last())
                  break;
            
               participants.findNext();
            }
         }
      }
   
    // Remove the ride from all three ride indexes
      removeFromIndex(ridesByPickupLocation, ride.getPickupLocation(), rideId);
   
      removeFromIndex(ridesByPickupTime,timeSlot.getStart(), rideId);
   
      ridesById.removeKey(rideId);
   
      return true;
   }
   
   public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
      return searchIndex(ridesByPickupLocation, pickupLocation);
   }


   public LinkedList<IRide> searchRidesByRiderName(String riderName) {
      IBST<Integer, IRide> result = new BST<Integer, IRide>();
   
      LinkedList<IRider> riders = searchRidersByName(riderName);
   
      if (!riders.empty()) {
         riders.findFirst();
      
         while (true) {
            LinkedList<IRide> rides = riders.retrieve().getSchedule().inOrder();
         
            if (!rides.empty()) {
               rides.findFirst();
            
               while (true) {
                  IRide ride = rides.retrieve();
                  result.insert(ride.getRideId(), ride);
               
                  if (rides.last())
                     break;
               
                  rides.findNext();
               }
            }
         
            if (riders.last())
               break;
         
            riders.findNext();
         }
      }
   
      return result.inOrder();
    
   }
   

   public LinkedList<IRider> getSharedRideParticipants(int rideId) {
   // Check if the ride exists
      if (!ridesById.findKey(rideId))
         return new LinkedList<IRider>();
   
      IRide ride = ridesById.retrieve();
   
    // Check if the ride is a shared ride
      if (ride instanceof ISharedRide)
         return ((ISharedRide) ride).getParticipants();
   
      return new LinkedList<IRider>();
   }


   public LinkedList<IRide> getAllRidesAlphabetically() {
    LinkedList<IRide> result = new LinkedList<IRide>();

    // Get the inner BSTs sorted by pickup location
    LinkedList<IBST<Integer, IRide>> locations = ridesByPickupLocation.inOrder();

    if (!locations.empty()) {
        locations.findFirst();

        while (true) {
            LinkedList<IRide> rides = locations.retrieve().inOrder();

            if (!rides.empty()) {
                rides.findFirst();

                while (true) {
                    result.insert(rides.retrieve());

                    if (rides.last())
                        break;

                    rides.findNext();
                }
            }

            if (locations.last())
                break;

            locations.findNext();
        }
    }

    return result;

   }

   public LinkedList<IRide> searchRidesByPickupTimeRange(IDateTime from, IDateTime to) {

    LinkedList<IRide> result = new LinkedList<IRide>();

    // Validate the time range
    if (from == null || to == null || from.compareTo(to) > 0)
        return result;

    // Get the inner BSTs within the time range
    LinkedList<IBST<Integer, IRide>> times = ridesByPickupTime.rangeSearch(from, to);

    if (!times.empty()) {
        times.findFirst();

        while (true) {
            LinkedList<IRide> rides = times.retrieve().inOrder();

            if (!rides.empty()) {
                rides.findFirst();

                while (true) {
                    result.insert(rides.retrieve());

                    if (rides.last())
                        break;

                    rides.findNext();
                }
            }

            if (times.last())
                break;

            times.findNext();
        }
    }

    return result;
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