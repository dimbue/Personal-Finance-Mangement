import java.util.Date;
public class Budget extends FinancialRecord {
    private double budgetLimit;
    private double currentExpenses;

    // Updated constructor to accept the additional parameters
    public Budget(Date date, double amount, String description, double budgetLimit, double currentExpenses) {
        super(date, amount, description);
        this.budgetLimit = budgetLimit;
        this.currentExpenses = currentExpenses;
    }


    @Override
    public String getDetails() {
        return String.format("Budget: %s - Limit: %.2f, Current Expenses: %.2f, Remaining: %.2f",
                budgetLimit, currentExpenses, calculateRemainingBudget());
    }


    @Override
    public double calculateTotal() {
        return budgetLimit - currentExpenses;
    }

    public void addExpense(double expense) {
        currentExpenses += expense;
    }

    public double calculateRemainingBudget() {
        return budgetLimit - currentExpenses;
    }
}

