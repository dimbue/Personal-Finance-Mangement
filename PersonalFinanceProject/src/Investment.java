import java.util.Date;

public class Investment extends FinancialRecord {
    private String investmentType;
    private double returns; // Keeping returns as a double to represent calculated returns

    // Updated constructor to include returns as an argument (type double)
    public Investment(Date date, double amount, String description, String investmentType, double returns) {
        super(date, amount, description); // Calling superclass constructor
        this.investmentType = investmentType;
        this.returns = returns; // Initializing returns with the provided value
    }

    @Override
    public String getDetails() {
        return "Investment on " + date + ": " + description + " of amount " + amount + " in " + investmentType + " with returns: " + returns;
    }

    @Override
    public double calculateTotal() {
        return amount + returns; // Returns calculated from the investment amount and returns rate
    }

    // Updated method to calculate returns based on a percentage
    public void calculateReturns(double returnPercentage) {
        returns = amount * returnPercentage / 100;
    }

    // Getter and setter methods for investmentType and returns can be added as needed
}

