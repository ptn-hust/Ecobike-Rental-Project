package group13.ecobikerental.views.invoice;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.home.HomeScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The screen handler for the invoice screen, displaying rental and payment information.
 */
public class InvoiceScreenHandler extends BaseScreenHandler implements Initializable {

    public Label lbRenter;
    public Label lbCardNumber;
    public Label lbDeposit;
    public Label lbRefund;
    public Label lbBikeBarcode;
    public Button btnReturnHome;
    public ImageView imgLogo;
    public Label lbRentaLFees;

    /**
     * Constructs a new InvoiceScreenHandler instance.
     * @param stage      The stage to display the screen on.
     * @param screenPath The path to the FXML screen file.
     * @throws IOException If an I/O error occurs while loading the screen.
     */
    public InvoiceScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImage(imgLogo, Configs.LOGO_IMG_PATH);

        btnReturnHome.setOnMouseClicked(mouseEvent -> {
            HomeScreenHandler homeScreenHandler = null;
            try {
                homeScreenHandler = new HomeScreenHandler(this.stage, Configs.HOME_SCREEN_PATH);
//                try {
//					homeScreenHandler.setController(new ViewInfoController());
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
                homeScreenHandler.setScreenTitle("Home Screen");
                homeScreenHandler.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Sets the rental and payment information on the invoice screen.
     */
    public void setInfo() {
        lbBikeBarcode.setText(Invoice.getInstance().getBike().getBikecode());
        lbCardNumber.setText(Invoice.getInstance().getPayTransaction().getCard().getCardCode());
        lbBikeBarcode.setText(Invoice.getInstance().getBike().getBikecode());
        lbDeposit.setText(Utils.getCurrencyFormat(Invoice.getInstance().getBike().getDeposit()));
        lbRenter.setText(Invoice.getInstance().getPayTransaction().getCard().getOwner());
        lbRefund.setText(Utils.getCurrencyFormat(Invoice.getInstance().getRefundTransaction().getAmount()));
        lbRentaLFees.setText(Utils.getCurrencyFormat(Invoice.getInstance().getRentalFee()));
    }
}
