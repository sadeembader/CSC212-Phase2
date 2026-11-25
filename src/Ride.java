// Common fields and methods of private and shared rides
public abstract class Ride implements IRide {
    protected final int rideId;
    protected String pickupLocation;
    protected String dropoffLocation;
    protected ITimeSlot timeSlot;
    protected IDriver driver;

    public Ride(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IDriver driver) {
        this.rideId = rideId;
    }

    public int getRideId() {
        return 0;
    }

    public String getPickupLocation() {
        return null;
    }

    public String getDropoffLocation() {
        return null;
    }

    public ITimeSlot getTimeSlot() {
        return null;
    }

    public IDriver getDriver() {
        return null;
    }

    public int compareTo(IRide other) {
        return 0;
    }
}
