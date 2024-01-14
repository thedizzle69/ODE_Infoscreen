module InfoScreen{
    requires javafx.controls;
    requires org.controlsfx.controls;
    requires javafx.fxml;
    //requires com.dlsc.formsfx.core;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    // requires jdk.incubator.foreign;
    //requires javafx.swing;
    //requires com.github.almasb.fxgl;

    // Add other module declarations if needed

    //The following lines are f***ing important!
    opens server to javafx.fxml; // Add this line to open the 'server' package to javafx.fxml
    opens client to javafx.fxml; // Add this line to open the 'client' package to javafx.fxml
    opens resources to javafx.fxml; // Add this line to open the 'resources' package to javafx.fxml



    // Export the package containing your Application class
    exports client;
    exports server;
    exports resources;

}
