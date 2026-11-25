// A time interval [start, end)
public class TimeSlot implements ITimeSlot {
    private IDateTime start;
    private IDateTime end;

    public TimeSlot(IDateTime start, IDateTime end) {
    }

    public IDateTime getStart() {
        return null;
    }

    public IDateTime getEnd() {
        return null;
    }

    public int compareTo(ITimeSlot other) {
        return 0;
    }
}
