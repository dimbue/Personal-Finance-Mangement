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

    public abstract String getDetails();
    public abstract double calculateTotal();
}
