import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class User {
    private String userID;
    private String name;
    private String accountDetails;
    protected ArrayList<FinancialRecord> financialRecords;
    private HashMap<String, FinancialRecord> recordMap; // Maps record IDs to FinancialRecord objects
    private Stack<FinancialRecord> recordHistory; // Stack for undo functionality
    private int recordCounter = 0; // Counter that will create unique record IDs once implemented correctly

    //Some of the methods have not been fully implemented yet- Am planning to allow for this in the final
    public User(String userID, String name, String accountDetails) {
        this.userID = userID;
        this.name = name;
        this.accountDetails = accountDetails;
        this.financialRecords = new ArrayList<>();
        this.recordMap = new HashMap<>(); // Initialize the HashMap
        this.recordHistory = new Stack<>(); // Initialize the Stack
    }

    public String getName() {
        return name;
    }

    // Add a financial record with a user-specified recordID
    public void addRecord(String recordID, FinancialRecord record) {
        financialRecords.add(record);
        recordMap.put(recordID, record); // Add to the HashMap for efficient lookup
        recordHistory.push(record); // Push to stack for undo functionality
    }

    // This will hopfully give a unique record ID
    public void addRecord(FinancialRecord record) {
        String recordID = "Record" + (++recordCounter); // Generate a unique record ID
        addRecord(recordID, record); // Call the primary addRecord method
    }

    // Undo the last financial record addition
    public void undoLastRecord() {
        if (!recordHistory.isEmpty()) {
            FinancialRecord lastRecord = recordHistory.pop(); // Remove from stack
            financialRecords.remove(lastRecord); // Remove from ArrayList
            recordMap.values().remove(lastRecord); // Remove from HashMap
            System.out.println("Undo: Removed record - " + lastRecord.getDetails());
        } else {
            System.out.println("No records to undo.");
        }
    }

    // Retrieve a financial record by its record ID
    public FinancialRecord getRecord(String recordID) {
        return recordMap.get(recordID); // Lookup in the HashMap
    }

    // Returns a list of all financial records
    public ArrayList<FinancialRecord> getFinancialRecords() {
        return financialRecords;
    }

    // Creates a financial summary (list of all records' details)
    public String getFinancialSummary() {
        StringBuilder summary = new StringBuilder("Financial Summary for " + name + ":\n");
        for (FinancialRecord record : financialRecords) {
            summary.append(record.getDetails()).append("\n");
        }
        return summary.toString();
    }
}
