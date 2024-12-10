import java.io.Serializable;
import java.util.Date;
public class Transaction extends FinancialRecord implements Serializable {
    private String category;
    private String transactionType;

        private String type;

        public Transaction(Date date, double amount, String description, String type) {
            super(date, amount, description); // Call superclass constructor
            this.type = type;
        }

        @Override
        public String getDetails() {
            return "Transaction on " + date + ": " + description + " of amount " + amount + " (" + type + ")";
        }

        @Override
        public double calculateTotal() {
            return amount;
        }

        public String getTransactionType() {
            return transactionType;
        }
    }

