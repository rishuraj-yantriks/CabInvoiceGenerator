import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class InvoiceServiceTest {
    private static InvoiceService invoiceService = null;
    private boolean premium = false;

    @Before
    public void setUp() {
        invoiceService = new InvoiceService();
    }

    @Test
    public void givenDistanceAndTime_shouldReturn_theTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance, time, premium);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturn_MinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance, time, premium);
        Assert.assertEquals(5, fare,0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnInvoiceSummary() {
        Ride[] rides = {new Ride(2.0, 5),new Ride(0.1,1)};
        InvoiceSummary summary = invoiceService.calculateFare(rides,premium);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserId_getListOfRides_shouldReturnInvoice() {
        String userId = "user01";
        Ride[] rides = {new Ride(2.0, 5),new Ride(0.1,1)};
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId, premium);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2,30.0);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }

    @Test
    public void givenDifferentRideTypes_shouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1,1)
        };
        Ride[] premiumRides ={
                new Ride(2.5,3),
                new Ride(4.0,4)
        };
        InvoiceSummary summary = invoiceService.calculateTotalFare(rides,premiumRides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(rides.length+premiumRides.length,141.5);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}
