package server;

import client.Content;
// import javafx.application.Platform;
// import javafx.beans.Observable;
// import javafx.collections.FXCollections;
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
// import java.nio.Buffer;

public class ServerController {

    @FXML
    private Label dynamicInfoLabel;

    @FXML
    private ListView<Content> lvListView= new ListView<>();

    @FXML
    private BorderPane root;

    @FXML
    private Label serverStatusLabel;

    @FXML
    private TextArea tfLogField;

    private ObservableList<Content> items=null;


    private ScreenOutput screenOutput;

    /**
     * Handles the display of content selected from the ListView.
     * It creates a ScreenOutput instance if not already created,
     * displays the selected content, and logs the action in the text area.
     */
    public void handleDisplayContent() {
        if (screenOutput == null) {
            screenOutput = new ScreenOutput();
        }
        int selectedIndex = lvListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Content selectedContent = items.get(selectedIndex);
            screenOutput.displayContent(selectedContent);
            items.remove(selectedIndex);

            tfLogField.appendText("\nDisplaying Element index: " + selectedIndex);
        }
    }

/*
    @FXML
    public void handleDisplayContent() {

        if (screenOutput == null) {
            screenOutput = new ScreenOutput();
        }
        // You need to implement how to get the content from the client
        String content = getContentFromClient();


        int selectedIndex=lvListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1)
        {
           ScreenOutput output= new ScreenOutput();
           output.displayContent(items.get(selectedIndex));
            items.remove(selectedIndex);

            tfLogField.appendText("\nDisplaying Element index:n"+selectedIndex);
        }


    }

*/
    /**
     * Updates the list view with the provided content list.
     *s
     * @param contentList The content list to be displayed in the list view.
     */
    public void updateListView(ObservableList<Content> contentList)
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
    public void setObservable(ObservableList<Content> items)
    {
        this.items=items;

    }

    /**
     * Retrieves content from the client. This is a placeholder method and
     * needs to be implemented according to the application logic.
     *
     * @return String representing the content from the client.
     */
    private String getContentFromClient() {
        // Return the content received from the client

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
        alert.setContentText("Path "+ ExportLog.getAbsolutePath().toString());
        alert.showAndWait();



    }
}