public class InvoiceGenerator {

    private static final double MINIMUM_COST_PER_KILOMETER = 10.0;
    private static final int COST_PER_KILOMETER = 1;
    private static final double MINIMUM_FARE = 5;
    private static final double PREMIUM_MINIMUM_COST_PER_KILOMETER = 15.0;
    private static final int PREMIUM_COST_PER_KILOMETER = 2;
    private static final double PREMIUM_MINIMUM_FARE = 20;



    public double calculateFare(double distance, int time) {
        double totalFare = distance * MINIMUM_COST_PER_KILOMETER + time * COST_PER_KILOMETER;
        return Math.max(totalFare,MINIMUM_FARE);
    }

    public double calculatePremiumFare(double distance, int time){
        double totalFare = distance * PREMIUM_MINIMUM_COST_PER_KILOMETER + time * PREMIUM_COST_PER_KILOMETER;
        return Math.max(totalFare,PREMIUM_MINIMUM_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0.0;
        for(Ride ride : rides){
            totalFare += calculateFare(ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public InvoiceSummary calculatePremiumFare(Ride[] rides) {
        double totalFare = 0.0;
        for(Ride ride : rides){
            totalFare += calculatePremiumFare(ride.distance,ride.time);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public InvoiceSummary calculateTotalFare(Ride[] rides, Ride[] premimumRides){
        InvoiceSummary invoiceSummary = this.calculateFare(rides);
        InvoiceSummary invoiceSummary1 = this.calculatePremiumFare(premimumRides);
        return new InvoiceSummary(rides.length+premimumRides.length,invoiceSummary.totalFare+invoiceSummary1.totalFare);
    }

}
