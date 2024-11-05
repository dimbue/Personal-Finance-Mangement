import java.util.ArrayList;

public class User {
    private String userID;
    private String name;
    private String accountDetails;
    public ArrayList<FinancialRecord> financialRecords;

    public User(String userID, String name, String accountDetails) {
        this.userID = userID;
        this.name = name;
        this.accountDetails = accountDetails;
        this.financialRecords = new ArrayList<>();
    }
    public String getName(){
        return name;
    }

    public void addRecord(FinancialRecord record) {
        financialRecords.add(record);
    }

    public String getFinancialSummary() {
        StringBuilder summary = new StringBuilder("Financial Summary for " + name + ":\n");
        for (FinancialRecord record : financialRecords) {
            summary.append(record.getDetails()).append("\n");
        }
        return summary.toString();
    }
}
