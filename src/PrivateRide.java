public class PrivateRide extends Ride implements IPrivateRide {
    private IRider rider;

    public PrivateRide(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IRider rider, IDriver driver) {
        super(rideId, pickupLocation, dropoffLocation, timeSlot, driver);
    }

    public IRider getRider() {
        return null;
    }

    public boolean hasRider(int riderId) {
        return false;
    }

    public String toString() {
        return "";
    }
}
