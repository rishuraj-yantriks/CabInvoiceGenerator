import java.util.ArrayList;
import java.util.List;

public class UserDetails {

    public String userId;
    public List<Ride> rides;
    public InvoiceSummary invoiceSummary;

    public UserDetails(String userId) {
        this.userId= userId;
    }

    public void addRides(Ride[] rides){
        this.rides = new ArrayList<>();
        for(Ride ride:rides)
            this.rides.add(ride);
    }
}
