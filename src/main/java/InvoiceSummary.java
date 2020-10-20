public class InvoiceSummary {
    public double totalFare;
    public int numberOfRides;
    public double AverageFare;

    public InvoiceSummary(int numberOfRides, double totalFare) {
        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
        this.AverageFare = this.totalFare/this.numberOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return Double.compare(that.totalFare, totalFare) == 0 &&
                numberOfRides == that.numberOfRides &&
                Double.compare(that.AverageFare, AverageFare) == 0;
    }

}
