import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "financial_records.dat";

    // Allows saving to file
    public static void saveRecords(ArrayList<FinancialRecord> records) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(records);
            System.out.println("Records saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Error saving records: " + e.getMessage());
        }
    }

    // Load records from a file
    public static ArrayList<FinancialRecord> loadRecords() {
        ArrayList<FinancialRecord> records = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            records = (ArrayList<FinancialRecord>) ois.readObject();
            System.out.println("Records loaded successfully from " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.out.println("No saved records found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading records: " + e.getMessage());
        }
        return records;
    }
}
