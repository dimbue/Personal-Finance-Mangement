import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class FinanceApp extends Application {

    private User user;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the user object
        user = new User("U001", "John Doe", "Account123");

        // Layouts
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10)); // Use Insets from javafx.geometry

        // Form fields for adding a financial record
        TextField dateField = new TextField();
        dateField.setPromptText("Date (YYYY-MM-DD)");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField typeField = new TextField();
        typeField.setPromptText("Type (Transaction, Budget, or Investment)");

        TextField extraField = new TextField();
        extraField.setPromptText("Extra Info (Category/Limit/Returns)");

        Button addRecordButton = new Button("Add Record");
        Label statusLabel = new Label();

        // Undo Button
        Button undoButton = new Button("Undo Last Transaction");
        undoButton.setDisable(true); // Disabled initially

        // Add Record Button Action
        addRecordButton.setOnAction(e -> {
            try {
                String dateString = dateField.getText();
                // Parse the string into a LocalDate object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                Date date = java.sql.Date.valueOf(localDate); // Convert to java.util.Date if needed

                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();
                String type = typeField.getText();
                String extra = extraField.getText();  // For investmentType, or budgetLimit

                FinancialRecord record = null;

                switch (type.toLowerCase()) {
                    case "transaction":
                        record = new Transaction(date, amount, description, extra, "Expense");
                        break;
                    case "budget":
                        record = new Budget(date, amount, description, Double.parseDouble(extra), 0.0);
                        break;
                    case "investment":
                        // Pass correct arguments: investmentType (String), returns (int)
                        record = new Investment(date, amount, description, extra, Integer.parseInt(extra));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid type: " + type);
                }

                user.addRecord(record);
                statusLabel.setText("Record added successfully!");
                undoButton.setDisable(false); // Enable the undo button

            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });


        // Undo Button Action
        undoButton.setOnAction(e -> {
            user.undoLastRecord();
            statusLabel.setText("Last record undone.");
            if (user.getFinancialSummary().isEmpty()) {
                undoButton.setDisable(true); // Disable if no records remain
            }
        });

        // Layout configuration
        layout.getChildren().addAll(
                new Label("Add Financial Record"),
                dateField, amountField, descriptionField, typeField, extraField,
                addRecordButton, undoButton, statusLabel
        );

        // Scene setup
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Finance Management");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
