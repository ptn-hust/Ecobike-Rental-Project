package group13.ecobikerental.views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SplashScreenHandler implements Initializable {

    public ImageView logo;

    /**
     * Initializes the SplashScreenHandler after loading the FXML screen.
     * @param url            The URL location of the FXML file.
     * @param resourceBundle The resource bundle to be used.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("assets/images/Splash.png");
        Image image = new Image(file.toURI().toString());
        logo.setImage(image);
    }
}
