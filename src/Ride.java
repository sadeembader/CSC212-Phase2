// Common fields and methods of private and shared rides
public abstract class Ride implements IRide {
   protected final int rideId;
   protected String pickupLocation;
   protected String dropoffLocation;
   protected ITimeSlot timeSlot;
   protected IDriver driver;

   public Ride(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IDriver driver) {
      if (timeSlot == null ||
         timeSlot.getStart() == null ||
         timeSlot.getEnd() == null ||
         timeSlot.getStart().compareTo(timeSlot.getEnd()) >= 0)
         throw new IllegalArgumentException("Invalid ride time interval");
   
      this.rideId = rideId;
      this.pickupLocation = pickupLocation;
      this.dropoffLocation = dropoffLocation;
      this.timeSlot = timeSlot;
      this.driver = driver;
   }

   @Override
   public int getRideId() {
      return rideId;
   }

   @Override
   public String getPickupLocation() {
      return pickupLocation;
   }
   
   @Override
   public String getDropoffLocation() {
      return dropoffLocation;
   }
   
   @Override
   public ITimeSlot getTimeSlot() {
      return timeSlot;
   }
   @Override
   
   public IDriver getDriver() {
      return driver;
   }
   
   
       // Check whether a rider participates in this ride
   @Override
    public abstract boolean hasRider(int riderId);
    
   @Override
   public int compareTo(IRide other) {
      return Integer.compare(rideId, other.getRideId());
   }
   
   
   @Override
    public String toString() {
      return "Ride ID: " + rideId
                + " | Pickup: " + pickupLocation
                + " | Drop-off: " + dropoffLocation
                + " | Pickup time: " + timeSlot.getStart().format()
                + " | Drop-off time: " + timeSlot.getEnd().format()
                + " | Driver ID: " + driver.getId();
   }
}
