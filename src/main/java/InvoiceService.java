public class InvoiceService {

    private static final double MINIMUM_COST_PER_KILOMETER = 10.0;
    private static final int COST_PER_KILOMETER = 1;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15.0;
    private static final int PREMIUM_COST_PER_KILOMETER = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;
    private RideRepository rideRepository = null;

    public InvoiceService() {
        this.rideRepository = new RideRepository();
    }

    public double calculateFare(double distance, int time, boolean premium) {
        if(premium==false){
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_KILOMETER;
        return Math.max(totalFare,MINIMUM_FARE);
        }
        double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_KILOMETER;
        return Math.max(totalFare,PREMIUM_MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides, boolean premium) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time, premium);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary calculateTotalFare(Ride[] rides, Ride[] premiumRides){
        InvoiceSummary invoiceSummary = this.calculateFare(rides,false);
        InvoiceSummary invoiceSummary1 = this.calculateFare(premiumRides,true);
        return new InvoiceSummary(rides.length+premiumRides.length,invoiceSummary.totalFare+invoiceSummary1.totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId,boolean premium) {
        return this.calculateFare(rideRepository.getRides(userId),premium);
    }
}
