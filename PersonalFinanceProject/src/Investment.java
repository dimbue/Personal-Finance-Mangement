import java.util.Date;
public class Investment extends FinancialRecord {
    private String investmentType;
    private double returns;

    public Investment(Date date, double amount, String description, String investmentType) {
        super(date, amount, description);
        this.investmentType = investmentType;
        this.returns = 0; // Initially, returns are zero
    }

    @Override
    public String getDetails() {
        return "Investment on " + date + ": " + description + " of amount " + amount + " in " + investmentType;
    }

    @Override
    public double calculateTotal() {
        return amount + returns;
    }

    public void calculateReturns(double returnPercentage) {
        returns = amount * returnPercentage / 100;
    }
}
