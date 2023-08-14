package group13.ecobikerental.views.bike;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.PaymentController;
import group13.ecobikerental.controller.ViewInfoController;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.paydeposit.CreditCardFormScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BikeInfoScreenHandler extends BaseScreenHandler implements Initializable {
    public ImageView imgBike;
    public Label lbType;
    public Label lbBarcode;
    public Label lbLicensePlate;
    public Label lbDockName;
    public Label lbDeposit;
    public Label lbPin;
    public HBox licensePlate;
    public HBox pin;
    public Button btnBack;
    public Button btnRent;
    public ImageView imgLogo;

    private Bike bike;

    /**
     * This method is constructor with current stage.
     *
     * @param stage      -
     * @param screenPath -
     *
     * @throws IOException
     */
    public BikeInfoScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    /**
     * this method set info of screen.
     */
    public void setInfo() {
        this.lbBarcode.setText(this.bike.getBikecode());
        this.lbDockName.setText(this.bike.getDockName());
        this.lbDeposit.setText(Utils.getCurrencyFormat(this.bike.getDeposit()));
        this.lbType.setText(this.bike.getType());
        if (this.bike.getType().equals("Standard e-bike")) {
            ElectricBike electricBike = (ElectricBike) this.bike;
            pin.setVisible(true);
            licensePlate.setVisible(true);
            lbLicensePlate.setText(electricBike.getLicensePlate());
            lbPin.setText(electricBike.getPin() + "%");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setImage(imgLogo, Configs.LOGO_IMG_PATH);
        this.setImage(imgBike, "assets/images/e-bike.jpg");

        btnRent.setOnAction(event -> {
            try {
                rentBike();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnBack.setOnMouseClicked(mouseEvent -> {
            this.getPrev().show();
        });
    }

    public void rentBike() throws IOException {
    	Invoice.setInstance(); // reset invoice
        Invoice.getInstance().setBike(this.bike);

        CreditCardFormScreenHandler creditCardFormScreen =
            new CreditCardFormScreenHandler(this.stage, Configs.PAY_DEPOSIT_SCREEN_PATH,this.bike.getDeposit());

        creditCardFormScreen.setScreenTitle("Pay Deposit Screen");
        creditCardFormScreen.setController(new PaymentController());
        creditCardFormScreen.setPrev(this);

        creditCardFormScreen.show();

    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
    
    public ViewInfoController getController() {
        return (ViewInfoController) super.getController();
    }
}
