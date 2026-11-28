public class PrivateRide extends Ride implements IPrivateRide {
   private IRider rider;

   public PrivateRide(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IRider rider, IDriver driver) {
      super(rideId, pickupLocation, dropoffLocation, timeSlot, driver);
      this.rider = rider;
   }
   
   @Override
   public IRider getRider() {
      return rider;
   }

   @Override
    public boolean hasRider(int riderId) {
        if (rider != null && rider.getId() == riderId)
            return true;

        return false;
    }

    // Include the ride type and rider ID in the description
    @Override
    public String toString() {
        return "Private | " + super.toString()
                + " | Rider: " + this.getRider().getId();
    }
}
