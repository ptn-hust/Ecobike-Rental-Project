package group13.ecobikerental.views.paydeposit;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.cj.util.Util;

import group13.ecobikerental.controller.PayDepositController;
//import group13.ecobikerental.controller.RentBikeController;
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

public class ConfirmDepositScreenHandler extends BaseScreenHandler implements Initializable {
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

	/**
	 * This method is constructor with current stage.
	 *
	 * @param stage      -
	 * @param screenPath -
	 *
	 * @throws IOException
	 */
	public ConfirmDepositScreenHandler(Stage stage, String screenPath, CreditCard card, String content)
			throws IOException {
		super(stage, screenPath);
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

	private void confirmPayDeposit() throws Exception {
		Map<String, String> result = this.getController().payDepositRequest(card, Invoice.getInstance().getBike().getDeposit(),
				content);
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

	public void setInfo() {
		this.lbCardholderName.setText(this.card.getOwner());
		this.lbCardNumber.setText(this.card.getCardCode());
		this.lbExpDate.setText(this.card.getDateExpired());
		this.lbContent.setText(this.content);
		this.lbAmount.setText(Utils.getCurrencyFormat(Invoice.getInstance().getBike().getDeposit()));
	}

	public PayDepositController getController() {
		return (PayDepositController) super.getController();
	}
}
