import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class InvoiceServiceTest {
    private static InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_shouldReturn_theTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturn_MinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare,0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),new Ride(0.1,1)};
        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserId_getListOfRides_shouldReturnInvoice() {
        UserDetails userDetails = new UserDetails("user01");
        Ride[] rides = {new Ride(2.0, 5),new Ride(0.1,1)};
        userDetails.addRides(rides);
        userDetails.invoiceSummary = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary,userDetails.invoiceSummary);
    }

    @Test
    public void givenDifferentRideTypes_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),new Ride(0.1,1)};
        Ride[] premiumRides ={new Ride(2.5,3),new Ride(4.0,4)};
        double totalFare=0;
        for(Ride ride:rides)
            totalFare += invoiceGenerator.calculateFare(ride.distance,ride.time);
        for(Ride ride:premiumRides)
            totalFare += invoiceGenerator.calculatePremiumFare(ride.distance,ride.time);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides.length+premiumRides.length,totalFare);
        InvoiceSummary invoiceSummary = invoiceGenerator.calculateTotalFare(rides,premiumRides);
        Assert.assertEquals(expectedInvoiceSummary,invoiceSummary);
    }
}
