package group13.ecobikerental.views.payment;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.DAL.bike.BikeDAL;
import group13.ecobikerental.controller.PaymentController;
import group13.ecobikerental.entity.creditcard.CreditCard;
import group13.ecobikerental.exception.InvalidCardException;
import group13.ecobikerental.exception.MissingFieldException;
import group13.ecobikerental.service.bike.BikeService;
import group13.ecobikerental.service.creditcard.CreditCardService;
import group13.ecobikerental.service.invoice.InvoiceService;
import group13.ecobikerental.subsystem.InterbankSubsystem;
import group13.ecobikerental.utils.Configs;

import group13.ecobikerental.views.BaseScreenHandler;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The screen handler for entering credit card information for payment.
 */

public class CreditCardFormScreenHandler extends BaseScreenHandler implements Initializable {
	public TextField tfCardholderName;
	public TextField tfCardNumber;
	public ComboBox<String> cbbIssuingBank;
	public TextField tfSecurityCode;
	public TextField tfExpirationDate;
	public TextField tfContent;

	public Button btnBack;
	public Button btnContinue;
	public ImageView imgLogo;

	private int amount;

	/**
	 * Constructs a new CreditCardFormScreenHandler instance.
	 * 
	 * @param stage      The stage to display the screen on.
	 * @param screenPath The path to the FXML screen file.
	 * @param amount     The amount of payment.
	 * @throws IOException If an I/O error occurs while loading the screen.
	 */
	public CreditCardFormScreenHandler(Stage stage, String screenPath, int amount) throws IOException {
		super(stage, screenPath);
		this.amount = amount;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new PaymentController(new InterbankSubsystem(), new CreditCardService(), new InvoiceService(),
					new BikeService(new BikeDAL())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btnContinue.setOnAction(event -> {
			try {
				System.out.println("Clicked: Continue");
				enterCardHandler();
			} catch (MissingFieldException e) {

				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Missing fields");
				alert.setContentText(e.getMessage()); // Display the exception message
				alert.showAndWait();
			} catch (InvalidCardException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid card format");
				alert.setContentText(
						"Expired Date has format mm/yy\nLength of security code is 3\nPlease enter again!!");
				alert.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		btnBack.setOnMouseClicked(mouseEvent -> {
			System.out.println("Clicked: Back");
			this.getPrev().show();
		});
	}

	private void enterCardHandler() throws IOException, MissingFieldException, InvalidCardException {
		String owner = tfCardholderName.getText();
		String cardCode = tfCardNumber.getText();
		String cvvCode = tfSecurityCode.getText();
		String expDate = tfExpirationDate.getText();
		String content = tfContent.getText();
		System.out.println(owner + " " + cardCode + " " + cvvCode + " " + expDate);
		if (content == null) {
			content = "Pay deposit to Ecobike Rental Company";
		}
		if (owner.isBlank() || cardCode.isBlank() || cvvCode.isBlank() || expDate.isBlank()) {
			throw new MissingFieldException("All fields must be required");
		}
		CreditCard card = new CreditCard(cardCode, owner, cvvCode, expDate);
		if (this.getController().checkCardInfoRequest(card)) {
			ConfirmPaymentScreenHandler confirmPaymentScreen = new ConfirmPaymentScreenHandler(this.stage,
					Configs.DEPOSIT_CONFIRM_SCREEN_PATH, this.amount, card, content);
			confirmPaymentScreen.setInfo();
			confirmPaymentScreen.setPrev(this);
			confirmPaymentScreen.setController(this.getController());
			confirmPaymentScreen.setScreenTitle("Confirm Deposit");
			confirmPaymentScreen.show();
		} else {
			throw new InvalidCardException();
		}
	}

	/**
	 * Returns the PaymentController associated with this screen.
	 * 
	 * @return The PaymentController instance.
	 */
	public PaymentController getController() {
		return (PaymentController) super.getController();
	}
}
