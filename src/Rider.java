public class Rider extends Person implements IRider {
    private String email;
    private String homeCity;

    public Rider(int id, String name, String email, String phoneNumber, String homeCity) {
        super(id, name, phoneNumber);
        this.email = email;
        this.homeCity = homeCity;
    }

    public String getEmail() {
        return email;
    }

    public String getHomeCity() {
        return homeCity;
    }

    // compare riders by ID
    public int compareTo(IRider other) {
        if (this.id < other.getId())
            return -1;
        else if (this.id > other.getId())
            return 1;
        else
            return 0;
    }

    // format rider details for printing
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Phone: " + phoneNumber + " | Email: " + email + " | City: " + homeCity;
    }
}