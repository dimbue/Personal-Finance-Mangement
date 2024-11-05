import java.util.ArrayList;
import java.util.Date;

public class Report {
    private String userID;
    private Date reportDate;
    private String reportType;
    private ArrayList<FinancialRecord> records;

    public Report(String userID, Date reportDate, String reportType) {
        this.userID = userID;
        this.reportDate = reportDate;
        this.reportType = reportType;
        this.records = new ArrayList<>();
    }

    public void addRecord(FinancialRecord record) {
        records.add(record);
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder("Report Type: " + reportType + " on " + reportDate + "\n");
        for (FinancialRecord record : records) {
            report.append(record.getDetails()).append("\n");
        }
        return report.toString();
    }
}
