public class SharedRide extends Ride implements ISharedRide {
   private IBST<Integer, IRider> riders;

   public SharedRide(int rideId, String pickupLocation, String dropoffLocation, ITimeSlot timeSlot, IDriver driver) {
      super(rideId, pickupLocation, dropoffLocation, timeSlot, driver);
      riders = new BST<Integer, IRider>();
   }

   @Override
   public LinkedList<IRider> getParticipants() {
      return riders.inOrder();
   }

   @Override
   public boolean addParticipant(IRider rider) {
      if (rider == null)
         return false;
   
      return riders.insert(rider.getId(), rider);
   }

   @Override
   public boolean removeParticipantById(int riderId) {
      return riders.removeKey(riderId);
   }

   @Override
   public boolean isEmpty() {
      return riders.empty();
   }

   @Override
   public boolean hasRider(int riderId) {
      return riders.findKey(riderId);
   }

// Return the ride details and participant IDs
   @Override
   public String toString() {
      String riderIDs = "";
   
      LinkedList<IRider> participants = getParticipants();
   
      if (!participants.empty()) {
      
         participants.findFirst();
      
         while (!participants.last()) {
            riderIDs += participants.retrieve().getId() + ";";
            participants.findNext();
         }
      
         riderIDs += participants.retrieve().getId();
      }
   
      return "SHARED," + super.toString() + "," + riderIDs;
   }
}
