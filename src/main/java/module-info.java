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
    //requires javafx.swing;
    //requires com.github.almasb.fxgl;

    // Add other module declarations if needed

    // Export the package containing your Application class
    exports client;
    exports server;
    exports resources;

}
