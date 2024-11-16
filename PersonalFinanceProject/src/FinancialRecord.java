import java.util.Date;

public abstract class FinancialRecord {
    protected Date date;
    protected double amount;
    protected String description;

    public FinancialRecord(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public String getDetails() {
        return String.format("Date: %s, Amount: %.2f, Description: %s", date, amount, description);
    }

    public abstract double calculateTotal();
}
