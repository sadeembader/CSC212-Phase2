public class Rider extends Person implements IRider {
    private String email;
    private String homeCity;

    public Rider(int id, String name, String email, String phoneNumber, String homeCity) {
        super(id, name, phoneNumber);
    }

    public String getEmail() {
        return null;
    }

    public String getHomeCity() {
        return null;
    }

    public int compareTo(IRider other) {
        return 0;
    }

    public String toString() {
        return "";
    }
}
