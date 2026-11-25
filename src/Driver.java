public class Driver extends Person implements IDriver {
    private String vehiclePlate;
    private VehicleType vehicleType;

    public Driver(int id, String name, String phoneNumber, String vehiclePlate, VehicleType vehicleType) {
        super(id, name, phoneNumber);
    }

    public String getVehiclePlate() {
        return null;
    }

    public VehicleType getVehicleType() {
        return null;
    }

    public int compareTo(IDriver other) {
        return 0;
    }

    public String toString() {
        return "";
    }
}
