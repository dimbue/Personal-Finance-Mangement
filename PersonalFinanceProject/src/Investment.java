import java.io.Serializable;
import java.util.Date;

public class Investment extends FinancialRecord implements Serializable {
    private String investmentType;
    private double returns; // Keeping returns as a double to represent calculated returns

    // Updated to include the returns as a double
    public Investment(Date date, double amount, String description, String investmentType, double returns) {
        super(date, amount, description); // Calling superclass constructor
        this.investmentType = investmentType;
        this.returns = returns;
    }

    @Override
    public String getDetails() {
        return "Investment on " + date + ": " + description + " of amount " + amount + " in " + investmentType + " with returns: " + returns;
    }

    @Override
    public double calculateTotal() {
        return amount + returns;
    }

    //This allows for the User to update their returns but they are also unable to make it negative so it is realistic.
    public void updateReturns(double newReturns) {
        if (newReturns >= 0) {
            this.returns = newReturns;
        } else {
            throw new IllegalArgumentException("Returns cannot be negative.");
        }
    }
    //Would allow for the functionality to calculate the return and then return the percentage
    public void calculateReturns(double returnPercentage) {
        if (returnPercentage >= 0 && returnPercentage <= 100) {
            this.returns = amount * returnPercentage / 100;
        } else {
            throw new IllegalArgumentException("Return percentage must be between 0 and 100.");
        }
    }


    //This method will return the current return value
    public double getReturns(){
        return returns;
    }


}

