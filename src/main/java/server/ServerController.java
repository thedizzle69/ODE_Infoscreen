package server;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class ServerController {

    @FXML
    private Label dynamicInfoLabel;

    @FXML
    private ListView<?> lvListView;

    @FXML
    private BorderPane root;

    @FXML
    private Label serverStatusLabel;

// You can add methods to update dynamic information
    public void updateDynamicInfo(String info) {
        dynamicInfoLabel.setText(info);
    }

    // Add other controller logic as needed
}
