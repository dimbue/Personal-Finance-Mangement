import java.util.Date;
public class Budget extends FinancialRecord {
    private double budgetLimit;
    private double currentExpenses;

    public Budget(Date date, double budgetLimit, String description) {
        super(date, 0, description); // No amount for budget, just description
        this.budgetLimit = budgetLimit;
        this.currentExpenses = 0;
    }

    @Override
    public String getDetails() {
        return "Budget: " + description + " with limit " + budgetLimit + ", current expenses " + currentExpenses;
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

