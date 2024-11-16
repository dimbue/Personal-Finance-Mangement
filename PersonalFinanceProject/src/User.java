import java.util.ArrayList;
import java.util.Stack;

public class User {
    private String userID;
    private String name;
    private String accountDetails;
    public ArrayList<FinancialRecord> financialRecords;
    private Stack<FinancialRecord> recordHistory;

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

    //Utilizing Stack to allow an Undo function
    public void undoLastRecord() {
        if (!recordHistory.isEmpty()) {
            FinancialRecord lastRecord = recordHistory.pop(); // Remove from stack
            financialRecords.remove(lastRecord); // Remove from main list
            System.out.println("Undo: Removed record - " + lastRecord.getDetails());
        } else {
            System.out.println("No records to undo.");
        }
    }
    public String getFinancialSummary() {
        StringBuilder summary = new StringBuilder("Financial Summary for " + name + ":\n");
        for (FinancialRecord record : financialRecords) {
            summary.append(record.getDetails()).append("\n");
        }
        return summary.toString();
    }
}
