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

    // Updated method to calculate returns based on a percentage
    public void calculateReturns(double returnPercentage) {
        returns = amount * returnPercentage / 100;
    }

    //This allows the user to make the updates themselves
    public void updateReturns(double newReturns){
        this.returns= newReturns;
    }

    //This method will return the current return value
    public double getReturns(){
        return returns;
    }


}

