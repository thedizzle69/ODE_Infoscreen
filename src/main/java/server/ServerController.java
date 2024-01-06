package server;

import client.Content;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class ServerController {

    @FXML
    private Label dynamicInfoLabel;

    @FXML
    private ListView<String> lvListView= new ListView<>();;

    @FXML
    private BorderPane root;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private TextArea tfLogField;

    private ObservableList<String> items=null;


    private ScreenOutput screenOutput;
    public ServerController() {
        this.screenOutput = new ScreenOutput(); // Initialize once
    }

    @FXML
    public void handleDisplayContent() {

        if (screenOutput != null) {
            String content = getContentFromClient();
            screenOutput.displayContent(content);
        } else {
            System.err.println("screenOutput is null in handleDisplayContent");
        }
    }
    public void setScreenOutput(ScreenOutput screenOutput) {
        this.screenOutput = screenOutput;
    }


        if (screenOutput == null) {
            screenOutput = new ScreenOutput();
        }
        // You need to implement how to get the content from the client
        String content = getContentFromClient();

        screenOutput.displayContent(lvListView.getSelectionModel().getSelectedItem().toString());

        int selectedIndex=lvListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1)
        {
            items.remove(selectedIndex);


            tfLogField.appendText("\nDisplaying Element index:n"+selectedIndex);
        }


    }


    /**
     * Updates the list view with the provided content list.
     *
     * @param contentList The content list to be displayed in the list view.
     */
    public void updateListView(ObservableList<String> contentList)
    {
        lvListView.setItems(contentList);

    }

    /**
     * Sets the text in the log text area.
     *
     * @param text The text to be set in the log text area.
     */
    public void setTextInTextArea(String text) {
    tfLogField.setText(text);
    }

    /**
     * Sets the observable list for the controller.
     *
     * @param items The observable list to be set.
     */
    public void setObservable(ObservableList<String> items)
    {
        this.items=items;

    }

    // Method to get content from the client, to be implemented as per your application logic
    private String getContentFromClient() {
        return "Content from client";
    }

    /**
     * Exports the log to a text file when the export log button is pressed.
     *
     * @param actionEvent The action event triggered by the button press.
     */
    public void ExportLogButtonPressed(ActionEvent actionEvent) {
        File ExportLog= new File("LogFile.txt");

        try {
            FileWriter myWriter= new FileWriter(ExportLog);
            BufferedWriter bufferedWriter= new BufferedWriter(myWriter);

            tfLogField.appendText("\n Logfile exported...\n");


            bufferedWriter.write(tfLogField.getText());
            bufferedWriter.close();



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Export Log sucessful!");
        alert.setContentText("Path"+ ExportLog.getAbsolutePath().toString());
        alert.showAndWait();



    }
}
