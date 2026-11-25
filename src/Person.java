// Common fields and methods of riders and drivers
public abstract class Person implements IPerson {
    protected final int id;
    protected String name;
    protected String phoneNumber;
    protected IBST<ITimeSlot, IRide> schedule;

    public Person(int id, String name, String phoneNumber) {
        this.id = id;
    }

    public int getId() {
        return 0;
    }

    public String getName() {
        return null;
    }

    public String getPhoneNumber() {
        return null;
    }

    public void setPhoneNumber(String phoneNumber) {
    }

    public IBST<ITimeSlot, IRide> getSchedule() {
        return null;
    }
}
