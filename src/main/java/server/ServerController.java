package server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
<<<<<<< HEAD
import javafx.scene.layout.BorderPane;

public class ServerController {

    public BorderPane root;
=======

public class ServerController {

>>>>>>> origin/diz2.0
    @FXML
    private Label serverStatusLabel;

    @FXML
    private Label dynamicInfoLabel;

    // You can add methods to update dynamic information
    public void updateDynamicInfo(String info) {
        dynamicInfoLabel.setText(info);
    }

    // Add other controller logic as needed
}
