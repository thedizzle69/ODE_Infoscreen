package server;

import client.Content;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

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


    private ScreenOutput screenOutput;

    @FXML
    public void handleDisplayContent() {





        if (screenOutput == null) {
            screenOutput = new ScreenOutput();
        }
        // You need to implement how to get the content from the client
        String content = getContentFromClient();
        screenOutput.displayContent(lvListView.getSelectionModel().getSelectedItem());
    }



    public void updateListView(ObservableList<String> contentList)
    {
        lvListView.setItems(contentList);

    //todo implement list view



    }

    public void setTextInTextArea(String text) {
    tfLogField.setText(text);
    }

    // Method to get content from the client, to be implemented as per your application logic
    private String getContentFromClient() {
        // Return the content received from the client
        // This is just a placeholder, you will need to implement the logic to retrieve actual content
        return "Content from client";
    }
}
