import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;

public class FinanceApp extends Application {
    private User user;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Financial Manager");

        // User input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter transaction amount");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter description");

        // Button to add transaction
        Button addButton = new Button("Add Transaction");
        addButton.setOnAction(e -> {
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();
            Date date = new Date(); // current date for simplicity
            Transaction transaction = new Transaction(date, amount, description, "General", "Credit");
            user.addRecord(transaction);
            amountField.clear();
            descriptionField.clear();
            updateSummary();
        });

        // Text area to display summary
        TextArea summaryArea = new TextArea();
        summaryArea.setEditable(false);

        VBox layout = new VBox(10, new Label("Name:"), nameField, amountField, descriptionField, addButton, summaryArea);
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateSummary() {
        if (user != null) {
            StringBuilder summary = new StringBuilder("Financial Summary for " + user.getName() + ":\n");
            for (FinancialRecord record : user.financialRecords) {
                summary.append(record.getDetails()).append("\n");
            }
            // Assume you have a reference to the TextArea to update here
            // summaryArea.setText(summary.toString()); // Uncomment and use in your code
        }
    }

    public static void main(String[] args) {
        // Initialize user
        FinanceApp app = new FinanceApp();
        app.user = new User("1", "John Doe", "12345"); // Example user
        launch(args);
    }
}
