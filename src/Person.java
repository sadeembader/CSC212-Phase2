public abstract class Person implements IPerson {
    protected final int id;
    protected String name;
    protected String phoneNumber;
    protected IBST<ITimeSlot, IRide> schedule;

    public Person(int id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        setPhoneNumber(phoneNumber);
        this.schedule = new BST<ITimeSlot, IRide>();
    }

    public void setPhoneNumber(String phoneNumber) {
        // phone must be 10 digits exactly
        if (phoneNumber == null || phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }

        // check if all characters are numbers
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                throw new IllegalArgumentException("Phone number must contain only digits.");
            }
        }

        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // the person's rides sorted by time slot
    public IBST<ITimeSlot, IRide> getSchedule() {
        return schedule;
    }
}