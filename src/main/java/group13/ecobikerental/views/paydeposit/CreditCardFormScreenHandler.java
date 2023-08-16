package group13.ecobikerental.views.paydeposit;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.PaymentController;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.exception.MissingFieldException;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.BaseScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

	public CreditCardFormScreenHandler(Stage stage, String screenPath, int amount) throws IOException {
		super(stage, screenPath);
		this.amount = amount;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
//		setImage(imgLogo, Configs.LOGO_IMG_PATH);
		btnContinue.setOnAction(event -> {
			try {
				requestToPay();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		btnBack.setOnMouseClicked(mouseEvent -> {
			this.getPrev().show();
		});
	}

//	public void setAmount(int amount) {
//		this.amount = amount;
//	}

	private void requestToPay() throws IOException {
		String owner = tfCardholderName.getText();
		String cardCode = tfCardNumber.getText();
		String cvvCode = tfSecurityCode.getText();
		String expDate = tfExpirationDate.getText();
		String content = tfContent.getText();
		if (content == null) {
			content = "Pay deposit to Ecobike Rental Company";
		}
		if(owner == null || cardCode == null || cvvCode == null || expDate == null) {
			throw new MissingFieldException();
		}

		if (this.getController().checkCardInfo(expDate, cvvCode)) {
			CreditCard card = new CreditCard(cardCode, owner, cvvCode, expDate);
			ConfirmPaymentScreenHandler confirmPaymentScreen = new ConfirmPaymentScreenHandler(this.stage,
					Configs.DEPOSIT_CONFIRM_SCREEN_PATH, this.amount, card, content);
			confirmPaymentScreen.setInfo();
			confirmPaymentScreen.setPrev(this);
			confirmPaymentScreen.setController(this.getController());
			confirmPaymentScreen.setScreenTitle("Confirm Deposit");
			confirmPaymentScreen.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid card format");
			alert.setContentText("Expired Date has format mm/yy\nLength of security code is 3\nPlease enter again!!");
			alert.showAndWait();
		}
	}

	public PaymentController getController() {
		return (PaymentController) super.getController();
	}
}
