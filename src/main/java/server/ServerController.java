package server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class ServerController {

    public BorderPane root;
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
