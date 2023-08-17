package group13.ecobikerental.views.paydeposit;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.cj.util.Util;

import group13.ecobikerental.controller.PaymentController;
//import group13.ecobikerental.controller.PaymentController;
import group13.ecobikerental.controller.ReturnBikeController;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.entity.payment.CreditCard;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.bike.BikeStatusScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The screen handler for confirming a payment for deposit.
 */
public class ConfirmPaymentScreenHandler extends BaseScreenHandler implements Initializable {
	public Label lbCardholderName;
	public Label lbCardNumber;
	public Label lbExpDate;
	public Label lbAmount;
	public Label lbContent;
	public Button btnBack;
	public Button btnConfirm;
	public ImageView imgLogo;

	private CreditCard card;
	private String content;
	private int amount;

	/**
     * Constructs a new ConfirmPaymentScreenHandler instance.
     * @param stage      The stage to display the screen on.
     * @param screenPath The path to the FXML screen file.
     * @param amount     The amount of payment.
     * @param card       The credit card information.
     * @param content    The content of the payment.
     * @throws IOException If an I/O error occurs while loading the screen.
     */
	public ConfirmPaymentScreenHandler(Stage stage, String screenPath,int amount, CreditCard card, String content)
			throws IOException {
		super(stage, screenPath);
		this.amount = amount;
		this.card = card;
		this.content = content;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		setImage(imgLogo, Configs.LOGO_IMG_PATH);
		btnConfirm.setOnAction(event -> {
			try {
				confirmPayDeposit();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		btnBack.setOnMouseClicked(mouseEvent -> {
			this.getPrev().show();
		});
	}

	/**
     * Confirms the payment for deposit and handles the result.
     * @throws Exception If an error occurs during payment processing.
     */
	private void confirmPayDeposit() throws Exception {
		Map<String, String> result = this.getController().payDepositRequest(card, this.amount, content);
		
		if (result.get("RESULT").equals("PAYMENT SUCCESSFUL!")) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle(result.get("RESULT"));
			alert.setContentText(result.get("MESSAGE"));
			alert.showAndWait();

//			jump to bike-status-screen
			BikeStatusScreenHandler bikeStatusScreen = new BikeStatusScreenHandler(this.stage,
					Configs.BIKE_STATUS_SCREEN_PATH);
			bikeStatusScreen.setController(new ReturnBikeController());
			bikeStatusScreen.setInfo();
			bikeStatusScreen.setScreenTitle("Bike Status");
			bikeStatusScreen.show();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle(result.get("RESULT"));
			alert.setContentText(result.get("MESSAGE"));
			alert.showAndWait();
		}
	}

	/**
     * Sets the payment information on the confirmation screen.
     */
	public void setInfo() {
		this.lbCardholderName.setText(this.card.getOwner());
		this.lbCardNumber.setText(this.card.getCardCode());
		this.lbExpDate.setText(this.card.getDateExpired());
		this.lbContent.setText(this.content);
		this.lbAmount.setText(Utils.getCurrencyFormat(Invoice.getInstance().getBike().getDeposit()));
	}

	/**
     * Returns the PaymentController associated with this screen.
     * @return The PaymentController instance.
     */
	public PaymentController getController() {
		return (PaymentController) super.getController();
	}
}
