package group13.ecobikerental.views.bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.DAL.bike.BikeDAL;
import group13.ecobikerental.DAL.dock.DockDAL;
import group13.ecobikerental.controller.ReturnBikeController;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.service.bike.BikeService;
import group13.ecobikerental.service.dock.DockService;
import group13.ecobikerental.service.invoice.InvoiceService;
import group13.ecobikerental.utils.Configs;

import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.returnbike.ReturnBikeScreenHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The screen handler for displaying the status of a bike that is currently in
 * use.
 */
public class BikeStatusScreenHandler extends BaseScreenHandler implements Initializable {
	public Label lbBike;
	public ImageView imgBike;
	public ProgressBar progressBarPin;
	
	public Label lbBarcode;
	public Label lbBrand;
	public Label lbLicensePlate;
	public Label lbBikeType;
	
	public Label lbPin;
	public Label lbTime;
	public Label pin;
	public Button btnReturn;
	public ImageView imgLogo;

	private int hours;
	private int minutes;
	private int seconds;
	private Timeline timeline;
	private Bike bike;

	/**
	 * Constructs a new BikeStatusScreenHandler instance.
	 * @param stage      The stage to display the screen on.
	 * @param screenPath The path to the FXML screen file.
	 * @throws IOException If an I/O error occurs while loading the screen.
	 */
	public BikeStatusScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.bike = Invoice.getInstance().getBike();
	}

	public void setInfo() {
		lbBike.setText(this.bike.getType() + " is running");
		if (this.bike.getType().equals("Standard e-bike")) {
			ElectricBike eBike = (ElectricBike) this.bike;
			progressBarPin.setVisible(true);
			lbPin.setVisible(true);
			pin.setText(eBike.getPin() + "%");
			lbLicensePlate.setText(eBike.getLicensePlate());
			
		}
		lbBikeType.setText(this.bike.getType());
		lbBarcode.setText(this.bike.getBikecode());
		lbBrand.setText(this.bike.getBrand());
	}

	/**
	 * Initializes the BikeStatusScreenHandler after loading the FXML screen.
	 * @param url            The URL location of the FXML file.
	 * @param resourceBundle The resource bundle to be used.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new ReturnBikeController(new BikeService(new BikeDAL()), new InvoiceService(),
					new DockService(new DockDAL())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(imgLogo, Configs.LOGO_IMG_PATH);
		setImage(imgBike, Configs.BIKE_RUNNING_IMG_PATH);

		// stop watch
		this.stopWatchInitialize();
	}

	private void stopWatchInitialize() {
		// set up the time display
		start();

		// set up the control buttons
		btnReturn.setOnMouseClicked(mouseEvent -> {
			try {
				System.out.println("Clicked: Return");
				pause();
				System.out.println("Duration: " + lbTime.getText());
				requestReturnHandler();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
	}

	private void start() {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), e -> {
			seconds++;
			setTime();
		});

		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}

	// stopwatch
	private void setTime() {
		// flip 60 seconds over to a minute
		if (seconds == 60) {
			seconds = 0;
			minutes++;
		}

		// flip 60 minutes over to an hour
		if (minutes == 60) {
			minutes = 0;
			hours++;
		}

		// ensure that values < 10 are padded with a 0
		String hourString = hours >= 10 ? String.valueOf(hours) : "0" + String.valueOf(hours);
		String minuteString = minutes >= 10 ? String.valueOf(minutes) : "0" + String.valueOf(minutes);
		String secondString = seconds >= 10 ? String.valueOf(seconds) : "0" + String.valueOf(seconds);

		lbTime.setText(hourString + ":" + minuteString + ":" + secondString);
	}

	private void pause() {
		timeline.pause();
	}

//	private void resume() {
//		timeline.play();
//	}

	public void requestReturnHandler() throws IOException, SQLException {
		Invoice.getInstance().setRentalTime(lbTime.getText());
		ReturnBikeScreenHandler returnBikeScreen = new ReturnBikeScreenHandler(this.stage,
				Configs.RETURN_BIKE_SCREEN_PATH);
		returnBikeScreen.setScreenTitle("Return Bike Screen");
		returnBikeScreen.setController(this.getController());
		returnBikeScreen.setInfo();
		returnBikeScreen.setPrev(this);
		returnBikeScreen.show();
	}

	/**
	 * Gets the associated controller for this screen handler.
	 * 
	 * @return The {@link ReturnBikeController} associated with this screen handler.
	 */
	public ReturnBikeController getController() {
		return (ReturnBikeController) super.getController();
	}

}
