package server;


import client.Content;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
// import resources.ContentType;

// import javax.imageio.ImageIO;
// import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
// import java.nio.file.Files;
import java.nio.file.Path;
// import java.util.Arrays;

/**
 * The `ScreenOutput` class handles the graphical display of content in a JavaFX application.
 * It is designed to show different types of content, including text and images, within the GUI.
 */
public class ScreenOutput {

    @FXML
    private ImageView IvImageView;

    @FXML
    private Label contentLabel;


    private final Stage stage;



    /**
     * Constructor for ScreenOutput. Initializes the primary stage and sets up the GUI.
     */
    public ScreenOutput() {
        this.stage = new Stage();
        initializeStage();

    }

    /**
     * Initializes the primary stage of the JavaFX application.
     * Loads the FXML file and sets up the scene for the stage.
     */
    private void initializeStage() {
        String fxmlPath = "src/main/java/resources/ScreenOutput.fxml";

        try {
            Path path = FileSystems.getDefault().getPath(fxmlPath);
            FXMLLoader loader = new FXMLLoader(path.toUri().toURL());
            loader.setController(this);

            Parent root = loader.load();




            //testDisplayImage();
            stage.setTitle("Output");

            stage.setScene(new Scene(root, 400, 200));  // Adjust the size as needed


        } catch (IOException e) {
            System.err.println("Error loading FXML file: ");
            e.printStackTrace();
        }

    }

    /**
     * Displays content on the GUI. The method determines the type of content (text or image)
     * and updates the appropriate GUI component.
     *
     * @param content The content to be displayed.
     */

    public void displayContent(Content content) {
        if(content.getData() instanceof String) {
            Platform.runLater(() -> contentLabel.setText((String) content.getData()));
        } else if (content.getData() instanceof byte[] imageBytes) {
            displayImage(imageBytes);
        }
        stage.show();
    }

    /**
     * Displays an image on the {@code ImageView}.
     * Converts a byte array representation of an image into an {@code Image} object for display.
     *
     * @param imageBytes The byte array representing the image.
     */
    private void displayImage(byte[] imageBytes) {
        System.out.println("Attempting to display image. Byte Array Length: " + imageBytes.length);
        Image image = new Image(new ByteArrayInputStream(imageBytes));
        Platform.runLater(() -> {
            IvImageView.setImage(image);
        });
    }

    /**
     * Parses a string representation of an image byte array and returns a byte array.
     * Used for converting image data into a displayable format.
     *
     * @param imageArray The string representation of the image byte array.
     * @return The parsed byte array of the image.
     */
    private byte[] parseImageArray(String imageArray) {
        String[] byteValues = imageArray.substring(1, imageArray.length() - 1).split(",");
        byte[] bytes = new byte[byteValues.length];

        for (int i = 0; i < byteValues.length; i++) {
            bytes[i] = Byte.parseByte(byteValues[i].trim());
        }

        return bytes;
    }
}



//Static image works. That comfirms that "ImageView" and JavaFX setup in ScreenOutput are functioning correctly.
//I guess, that the issue, therefore, is likely related to how the image data is being handled when sent as a byte array from cleint to the server

    /*
    private void testDisplayImage() {
        Image testImage = new Image("file:///C:/Users/tahir/Pictures/attack.png"); // Using forward slashes
        Platform.runLater(() -> IvImageView.setImage(testImage));
    }
*/




    /*
    public void displayContent(Content content) {

        if(content.getData() instanceof String)
        {
            System.out.println(content.getData().toString());

            Platform.runLater(() -> {
                contentLabel.setText(content.getData().toString());});

        } else if (content.getData() instanceof byte[]) {
            byte[] receivedArray=(byte[])content.getData();

            displayImage(Arrays.toString(receivedArray));
            //todo implement display. You don't say?
        }




        stage.show(); // Show the stage when content is updated

    }
*/

/**
 * Displays an image on the ImageView. Converts a string representation of an image byte array
 * into an Image object for display.
 *
 * @param imageArray The string representation of the image byte array.
 */


   /* private void displayImage(String imageArray) {

        System.out.println(imageArray);
        byte[] imageBytes = parseImageArray(imageArray);

        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            Platform.runLater(() -> {
                IvImageView.setImage(image);
            });
        }
    }
    */