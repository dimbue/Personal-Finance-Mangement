import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Date;

public class User {
    private String userID;
    private String name;
    private String accountDetails;
    protected ArrayList<FinancialRecord> financialRecords;
    private HashMap<String, FinancialRecord> recordMap; // Maps record IDs to FinancialRecord objects
    private Stack<FinancialRecord> recordHistory; // Stack for undo functionality
    private FinancialRecordTree recordTree;//This stores the FinancialRecordTree

    //Some of the methods have not been fully implemented yet- Am planning to allow for this in the final
    public User(String userID, String name, String accountDetails) {
        this.userID = userID;
        this.name = name;
        this.accountDetails = accountDetails;
        //These intialize and contribute to the population of the tree and records
        this.financialRecords = FileManager.loadRecords();
        this.recordMap = new HashMap<>(); // Initialize the HashMap
        this.recordHistory = new Stack<>(); // Initialize the Stack
        this.recordTree= new FinancialRecordTree();//Financial Tree is intitialized
        for(FinancialRecord record: financialRecords){
            recordTree.add(record);//This will add the existing records to the tree
        }
    }

    public void saveRecords(){
        FileManager.saveRecords(financialRecords);
    }



    // Add a financial record with a user-specified recordID
    public void addRecord(FinancialRecord record) {
        financialRecords.add(record);
        String recordID = null;
        recordMap.put(recordID,record); // Add to the HashMap for efficient lookup
        recordHistory.push(record); // Push to stack for undo functionality
        saveRecords();//This will help the save the information immediately after adding.
    }

    public FinancialRecord searchByDate(Date date){
        return recordTree.search(date);//This will allow for the search in the tree
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
