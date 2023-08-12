package group13.ecobikerental.views.bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.ReturnBikeController;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.return_bike.ReturnBikeScreenHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BikeStatusScreenHandler extends BaseScreenHandler implements Initializable {
	public Label lbBike;
	public ImageView imgBike;
	public ProgressBar progressBarPin;
	public Label lbPin;
	public Label lbTime;
	public HBox pin;
	public Button btnReturn;
	public ImageView imgLogo;

	private int hours;

	private int minutes;

	private int seconds;

	private Timeline timeline;

	private Bike bike;

	/**
	 * This method is constructor with current stage.
	 *
	 * @param stage      -
	 * @param screenPath -
	 *
	 * @throws IOException
	 */
	public BikeStatusScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		this.bike = Invoice.getInstance().getBike();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		setImage(imgLogo, Configs.LOGO_IMG_PATH);
		setImage(imgBike, Configs.BIKE_RUNNING_IMG_PATH);

//      stop watch
		this.stopWatchInitialize();

//		btnReturn.setOnMouseClicked(mouseEvent -> {
//			try {
//				requestToReturnBike();
//			} catch (IOException | SQLException e) {
//				e.printStackTrace();
//			}
//		});

	}

	private void stopWatchInitialize() {
		// set up the time display
		start();

		// set up the control buttons
		btnReturn.setOnMouseClicked(mouseEvent -> {
			try {
				pause();
				System.out.println("check 2: " + lbTime.getText());
				requestToReturnBike();
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public void requestToReturnBike() throws IOException, SQLException {
		Invoice.getInstance().setRentalTime(lbTime.getText());
		ReturnBikeScreenHandler returnBikeScreen = new ReturnBikeScreenHandler(this.stage,
				Configs.RETURN_BIKE_SCREEN_PATH);
		returnBikeScreen.setScreenTitle("Return Bike Screen");
		returnBikeScreen.setController(this.getController());
		returnBikeScreen.setInfo();
		returnBikeScreen.setPrev(this);
		returnBikeScreen.show();
	}

	public void setInfo() {
		lbBike.setText(this.bike.getType() + " is running");
		if (this.bike.getType().equals("Standard e-bike")) {
			ElectricBike eBike = (ElectricBike) this.bike;
			progressBarPin.setVisible(true);
			pin.setVisible(true);
			lbPin.setText(eBike.getPin() + "%");
		}
	}

	/**
	 * @return - {@link ReturnBikeController}
	 */
	public ReturnBikeController getController() {
		return (ReturnBikeController) super.getController();
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

	private void start() {
		KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), e -> {
			seconds++;
			setTime();
			// init a bike rent info entity
//			bikeRentInfo.setHours(hours);
//			bikeRentInfo.setMinutes(minutes);
//			bikeRentInfo.setSeconds(seconds);
			System.out.println("check check: " + lbTime.getText());
//			Invoice.getInstance().setRentalTime(lbTime.getText());
		});

		timeline = new Timeline(keyFrame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();
	}
	
	private void pause() {
        timeline.pause();
    }

    private void resume() {
        timeline.play();
    }
}
