import java.util.Date;
public class Transaction extends FinancialRecord {
    private String category;
    private String transactionType;

    public Transaction(Date date, double amount, String description, String category, String transactionType) {
        super(date, amount, description);
        this.category = category;
        this.transactionType = transactionType;
    }

    @Override
    public String getDetails() {
        return "Transaction on " + date + ": " + description + " of amount " + amount + " in category " + category;
    }

    @Override
    public double calculateTotal() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
