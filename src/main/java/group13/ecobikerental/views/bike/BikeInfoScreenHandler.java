package group13.ecobikerental.views.bike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.RentBikeController;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.invoice.Invoice;

import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;

import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.payment.CreditCardFormScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BikeInfoScreenHandler extends BaseScreenHandler implements Initializable {
	public Button btnBack;
	public Button btnRent;
	public HBox licensePlate;
	public ImageView imgBike;
	public Label lbType;
	public Label lbBarcode;
	public Label lbLicensePlate;
	public Label lbDockName;
	public Label lbDeposit;
	public Label lbPin;
	public ImageView imgLogo;

	private Bike bike;

	public BikeInfoScreenHandler(Stage stage, String screenPath, Bike bike) throws IOException {
		super(stage, screenPath);
		this.bike = bike;
	}

	/**
	 * Sets the information to be displayed on the screen.
	 */
	public void setInfo() {
		this.lbBarcode.setText(this.bike.getBikecode());
		this.lbDockName.setText(this.bike.getDockName());
		this.lbDeposit.setText(Utils.getCurrencyFormat(this.bike.getDeposit()));
		this.lbType.setText(this.bike.getType());
		if (this.bike.getType().equals("Standard e-bike")) {
			ElectricBike electricBike = (ElectricBike) this.bike;
			licensePlate.setVisible(true);
			lbLicensePlate.setText(electricBike.getLicensePlate());
			lbPin.setText(electricBike.getPin() + "%");
		}
	}

	/**
	 * Initializes the BikeInfoScreenHandler after loading the FXML screen.
	 * 
	 * @param url            The URL location of the FXML file.
	 * @param resourceBundle The resource bundle to be used.
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		this.setImage(imgLogo, Configs.LOGO_IMG_PATH);
		this.setImage(imgBike, "assets/images/e-bike.png");

		btnRent.setOnAction(event -> {
			try {
				System.out.println("Clicked: Rent bike");
				rentBike();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		btnBack.setOnMouseClicked(mouseEvent -> {
			System.out.println("Clicked: Back");
			this.getPrev().show();
		});
	}

	/**
	 * Handles the process of renting a bike.
	 * 
	 * @throws IOException If an I/O error occurs while navigating to the payment
	 *                     screen.
	 */
	public void rentBike() throws IOException {
		Invoice.setInstance(); // reset invoice
		Invoice.getInstance().setBike(this.bike);
		Invoice.getInstance().setPaymentMethod("Normal");

		CreditCardFormScreenHandler creditCardFormScreen = new CreditCardFormScreenHandler(this.stage,
				Configs.PAY_DEPOSIT_SCREEN_PATH, this.bike.getDeposit());

		creditCardFormScreen.setScreenTitle("Pay Deposit Screen");
		creditCardFormScreen.setPrev(this);
		creditCardFormScreen.show();
	}

	public RentBikeController getController() {
		return (RentBikeController) super.getController();
	}
}
