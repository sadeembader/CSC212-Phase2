public class SharedRide extends Ride implements ISharedRide {
    private IBST<Integer, IRider> participants;

    public SharedRide(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IDriver driver) {
        super(rideId, pickupLocation, dropoffLocation, timeSlot, driver);
    }

    public LinkedList<IRider> getParticipants() {
        return new LinkedList<IRider>();
    }

    public boolean addParticipant(IRider rider) {
        return false;
    }

    public boolean removeParticipantById(int riderId) {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean hasRider(int riderId) {
        return false;
    }

    public String toString() {
        return "";
    }
}
