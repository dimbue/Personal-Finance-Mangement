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
    private ListView<String> recordListView; // This ListView will allow for the user to see listed summary
    private TextArea recordDetailsArea; // This will show the details
    private FinancialRecordTree recordTree;//This is to manage the tree structure
    private FinancialRecord foundRecord;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the user object
        user = new User("U001", "John Doe", "Account123");
        recordTree= new FinancialRecordTree();//Intializing the Record Tree



        // Main layout using BorderPane
        BorderPane layout = new BorderPane();
        VBox formLayout = new VBox(10);
        formLayout.setPadding(new Insets(10));

        // The fields to type in the information
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

        // Record ListView
        recordListView = new ListView<>();
        recordListView.setPrefWidth(200); // Changing the width of view
        recordListView.setMinWidth(250);
        recordListView.setMaxWidth(Double.MAX_VALUE);

        // Record Details Area
        recordDetailsArea = new TextArea();
        recordDetailsArea.setEditable(false);
        recordDetailsArea.setPrefWidth(300);

        // Add Record Button Action
        addRecordButton.setOnAction(e -> {
            try {
                String dateString = dateField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateString, formatter);
                Date date = java.sql.Date.valueOf(localDate);

                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();
                String type = typeField.getText();
                String extra = extraField.getText();

                FinancialRecord record = null;

                switch (type.toLowerCase()) {
                    case "transaction":
                        record = new Transaction(date, amount, description, "Expense");
                        break;
                    case "budget":
                        record = new Budget(date, amount, description, Double.parseDouble(extra), 0.0);
                        break;
                    case "investment":
                        record = new Investment(date, amount, description, extra, 0.0); // Initial Return will be 0.0
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid type: " + type);
                }

                user.addRecord(record);
                recordTree.add(record);//This will add the record to the tree
                recordListView.getItems().add(record.getDetails());
                statusLabel.setText("Record added successfully!");
                undoButton.setDisable(false);

            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
            }
        });

        // Undo Button Action
        undoButton.setOnAction(e -> {
            user.undoLastRecord();
            recordListView.getItems().remove(recordListView.getItems().size() - 1);
            statusLabel.setText("Last record undone.");
            if (user.getFinancialSummary().isEmpty()) {
                undoButton.setDisable(true);
            }
        });

        // Record ListView Selection Action
        recordListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int selectedIndex = recordListView.getSelectionModel().getSelectedIndex();
                FinancialRecord selectedRecord = user.financialRecords.get(selectedIndex);
                recordDetailsArea.setText(selectedRecord.getDetails());
            }
        });

        //TextField to search for the date
        TextField searchDateField = new TextField();
        searchDateField.setPromptText("Enter date to search (YYYY-MM-DD");

        Button searchButton = new Button("Search Record");
        Button printAllButton = new Button("Print All Records");

        //Layout for search
        VBox searchLayout = new VBox(10, new Label("Search Record by Date"), searchDateField, searchButton);
        searchLayout.setPadding(new Insets(10));
        formLayout.getChildren().add(searchLayout);


        searchButton.setOnAction(e -> {
            try {
                String dateInput = searchDateField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateInput, formatter);
                Date someDate = java.sql.Date.valueOf(localDate); // Define someDate here

                FinancialRecord foundRecord = user.searchByDate(someDate); // Perform the search
                if (foundRecord != null) {
                    recordDetailsArea.setText(foundRecord.getDetails());
                } else {
                    recordDetailsArea.setText("No record found for the given date.");
                }
            } catch (Exception ex) {
                recordDetailsArea.setText("Error: " + ex.getMessage());
            }
        });
        printAllButton.setOnAction(e -> {
            recordDetailsArea.clear();
            recordTree.printInOrder();//Prints to the console
        });



        formLayout.getChildren().addAll(
                new Label("Add Financial Record"),
                dateField, amountField, descriptionField, typeField, extraField,
                addRecordButton, undoButton, statusLabel,
                new Label("Search Financial Record"),
                searchDateField, searchButton,
                printAllButton
        );


        //Making the box a bit more user friendly and easier on the eyes
        HBox mainContent = new HBox(10, recordListView, recordDetailsArea);
        mainContent.setHgrow(recordListView, Priority.ALWAYS);
        layout.setTop(formLayout);
        layout.setCenter(mainContent);
        layout.setStyle("-fx-background-color: tan;");

        // Scene setup
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Finance Management");
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}

