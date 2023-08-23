package group13.ecobikerental.views;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML handler
 */
public class FXMLScreenHandler {
	/**
     * The FXMLLoader instance used to load the FXML screen.
     */
    protected FXMLLoader loader;
    /**
     * The AnchorPane containing the loaded FXML screen content
     * use loader.load()
     */
    protected AnchorPane content;

    /**
     * Constructor to create a new instance of FXMLScreenHandler with the specified FXML screen path.
     * @param screenPath The path to the FXML screen file.
     * @throws IOException If an I/O error occurs while loading the FXML screen file.
     */
    public FXMLScreenHandler(String screenPath) throws IOException {
        this.loader = new FXMLLoader(getClass().getResource(screenPath));
        // Set this class as the controller
        this.loader.setController(this);
        this.content = loader.load();
    }

    /**
     * Gets the FXMLLoader instance
     * @return The FXMLLoader instance.
     */
    public FXMLLoader getLoader() {
        return loader;
    }

    /**
     * Sets the FXMLLoader instance
     * @param loader The FXMLLoader instance to set.
     */
    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    /**
     * Gets the AnchorPane containing the loaded FXML screen content.
     * @return The AnchorPane containing the screen content.
     */
    public AnchorPane getContent() {
        return content;
    }

    /**
     * Sets the AnchorPane containing the loaded FXML screen content.
     * @param content The AnchorPane to set as the screen content.
     */
    public void setContent(AnchorPane content) {
        this.content = content;
    }

    /**
     * Sets the specified image on the given ImageView.
     * @param imv  The ImageView to set the image on.
     * @param path The path of the image.
     */
    public void setImage(ImageView imv, String path) {
        File file = new File(path);
        Image img = new Image(file.toURI().toString());
        imv.setImage(img);
    }
    
    /**
     * Sets the image on the given ImageView using the provided URL, and sets an alternative image if the URL is not found.
     * @param imv       The ImageView to set the image on.
     * @param img_url   The URL of the image.
     * @param alt_path  The path of the alternative image to be used if the URL is not found.
     */
    public void setImage(ImageView imv, String img_url, String alt_path) {
        try {
            Image img = new Image(img_url);
            imv.setImage(img);
        } catch (Exception e) {
            // Set an alternative image if the URL is not found
            File file = new File(alt_path);
            Image img = new Image(file.toURI().toString());
            imv.setImage(img);
        }
    }
}
