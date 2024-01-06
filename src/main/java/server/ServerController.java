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

    @FXML
    public void handleDisplayContent() {

        if (screenOutput == null) {
            screenOutput = new ScreenOutput();
        }
        // You need to implement how to get the content from the client
        String content = getContentFromClient();
        screenOutput.displayContent(lvListView.getSelectionModel().getSelectedItem());

        int selectedIndex=lvListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1)
        {
            items.remove(selectedIndex);

            tfLogField.appendText("Displaying Element index:n"+selectedIndex);
        }


    }



    public void updateListView(ObservableList<String> contentList)
    {
        lvListView.setItems(contentList);

    }

    public void setTextInTextArea(String text) {
    tfLogField.setText(text);
    }

    public void setObservable(ObservableList<String> items)
    {
        this.items=items;

    }

    // Method to get content from the client, to be implemented as per your application logic
    private String getContentFromClient() {
        // Return the content received from the client
        // This is just a placeholder, you will need to implement the logic to retrieve actual content
        return "Content from client";
    }

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
