package group13.ecobikerental.views;

import java.io.IOException;
import java.util.Hashtable;

import group13.ecobikerental.controller.BaseController;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.home.HomeScreenHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * BaseScreenHandler class represents a handler for displaying JavaFX screens.
 * It encapsulates the functionality to manage scenes and stages for different screens.
 */
public class BaseScreenHandler extends FXMLScreenHandler {
    /**
     * scene of screen.
     */
    private Scene scene;
    /**
     * previous screen.
     */
    private BaseScreenHandler prev;
    /**
     * stage that scene show.
     */
    protected final Stage stage;
    /**
     * controller of screen.
     */
    private BaseController controller;

    /**
     * Constructs a new instance of the {@code BaseScreenHandler} class with the specified screen path.
     * @param screenPath The path to the FXML screen file.
     * @throws IOException If an I/O error occurs while loading the screen.
     */
    private BaseScreenHandler(String screenPath) throws IOException {
        super(screenPath);
        this.stage = new Stage();
    }

    /**
     * Constructor to create a new instance of BaseScreenHandler with the specified stage and screen path.
     * @param stage      The stage to associate with this screen handler.
     * @param screenPath The path to the FXML screen file.
     * @throws IOException If an I/O error occurs while loading the FXML screen file.
     */
    public BaseScreenHandler(Stage stage, String screenPath) throws IOException {
        super(screenPath);
        this.stage = stage;
    }

    /**
     * Gets the associated controller for this screen handler.
     * @return The associated controller.
     */
    public BaseController getController() {
        return controller;
    }

    /**
     * Sets the controller for this screen handler.
     * @param controller The controller to set.
     */
    public void setController(BaseController controller) {
        this.controller = controller;
    }

    /**
     * Sets the title of the stage associated with this screen handler.
     * @param title The title to set.
     */
    public void setScreenTitle(String title) {
        this.stage.setTitle(title);
    }

    /**
     * Displays the screen associated with this screen handler.
     */
    public void show() {
        if (this.scene == null) {
            this.scene = new Scene(this.content);
        }
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    /**
     * Gets the previous BaseScreenHandler.
     * @return The previous BaseScreenHandler.
     */
    public BaseScreenHandler getPrev() {
        return prev;
    }

    /**
     * Sets the previous BaseScreenHandler.
     * @param prev The previous BaseScreenHandler to set.
     */
    public void setPrev(final BaseScreenHandler prev) {
        this.prev = prev;
    }

    /**
     * Creates a new HomeScreenHandler associated with the same stage.
     * @return A new HomeScreenHandler instance.
     * @throws IOException If an I/O error occurs while loading the HomeScreen FXML file.
     */
    public HomeScreenHandler getHomeScreenHandler() throws IOException {
        return new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
    }
}
