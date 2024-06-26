package group13.ecobikerental;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.home.HomeScreenHandler;

/**
 * Starts the EcoBikeRental application.
 */
public class EcoBikeRentalApplication extends Application {
	/**
     * Entry point of the application.
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) throws IOException {
        StackPane root = (StackPane) FXMLLoader.load(getClass().getResource(Configs.SPLASH_SCREEN_PATH));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        // Load splash screen with fade in effect
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        // Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        // After fade in, start fade out
        fadeIn.play();
        fadeIn.setOnFinished((e) -> {
            fadeOut.play();
        });

        fadeOut.setOnFinished(event -> {
            try {
                HomeScreenHandler homeHandler = new HomeScreenHandler(stage, Configs.HOME_SCREEN_PATH);
                homeHandler.setScreenTitle("Home Screen");
                homeHandler.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
    
    /**
     * Launches the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
//    public static void main(String[] args) {
//        EcoBikeRentalApplication.main(args);
//    }
}