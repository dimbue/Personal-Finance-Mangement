import java.io.Serializable;
import java.util.Date;
public abstract class FinancialRecord implements Serializable {

    protected Date date;
    protected double amount;
    protected String description;

    public FinancialRecord(Date date, double amount, String description) {
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public Date getDate() {
        return date;  // Return the date associated with the financial record
    }

    public abstract String getDetails();
    public abstract double calculateTotal();

}
