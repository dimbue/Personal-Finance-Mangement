import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

/*This class is supposed to be in charge of running background tasks while the program is going on. With a more complex
* program this would be running the calculations, summaries, trends, and more in the background so that it doesn't
* overwhelm the UI and freeze the program for the user.*/

public class FinancialAnalyzer {
    //THis is related to the user that will have its financial records analyzed.
    private final User user;

    public FinancialAnalyzer(User user) {
        this.user = user;
    }

    public void analyzeRecords() {
        //This is the creation of a background task for analysis
        Task<Void> analysisTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                //This would go through the users financial transactions and such.
                for (int i = 0; i < user.financialRecords.size(); i++) {
                    FinancialRecord record = user.financialRecords.get(i);
                    // Simulate analysis
                    Thread.sleep(100);
                    //THis would show the update progress in the UI
                    updateProgress(i + 1, user.financialRecords.size());
                }
                return null;
            }
        };

        //This creates a progress bar for the UI
        ProgressBar progressBar = new ProgressBar();
        //While this links the progress property to the task progress
        progressBar.progressProperty().bind(analysisTask.progressProperty());

        //Executes the task in a new thread so that there is no blocking the UI
        Thread analysisThread = new Thread(analysisTask);
        analysisThread.setDaemon(true);
        analysisThread.start();
    }
}
