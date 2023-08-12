package group13.ecobikerental.views.return_bike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.ReturnBikeController;
import group13.ecobikerental.data_access_layer.DockDAL;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.bike.ElectricBike;
import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.entity.invoice.Invoice;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.utils.Utils;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.invoice.InvoiceScreenHandler;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class ReturnBikeScreenHandler extends BaseScreenHandler implements Initializable {
	public ImageView imgBike;
    public RowConstraints rowPin;
    public Label lbBarcode;
    public Label lbLicensePlate;
    public Label lbTypeBike;
    public Label lbPin;
    public Label lbRentalTime;
    public Label lbRentalFees;
    public ComboBox<String> cbReturnDock;
    public HBox licensePlate;
    public HBox pin;
    public Button btnBack;
    public Button btnReturn;
    public ImageView imgLogo;

    private String rentalTime;
    private int rentalFee;
    private Bike bike;
    private Timeline timeline;

    /**
     * This method is constructor with current stage.
     *
     * @param stage      -
     * @param screenPath -
     *
     * @throws IOException
     */
    public ReturnBikeScreenHandler(Stage stage, String screenPath) throws IOException {
        super(stage, screenPath);
        this.rentalTime = Invoice.getInstance().getRentalTime();
        this.bike = Invoice.getInstance().getBike();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImage(imgLogo, Configs.LOGO_IMG_PATH);
        setImage(imgBike, "assets/images/e-bike.jpg");
        btnBack.setVisible(false);

        btnReturn.setOnMouseClicked(mouseEvent -> {
            String dockReturn = cbReturnDock.getValue();
            System.out.println(dockReturn);

            try {
                returnBike(dockReturn, lbRentalTime.getText());
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });

//        btnBack.setOnMouseClicked(mouseEvent -> {
//            this.getPrev().show();
//        });

    }

    public void setInfo() throws SQLException {
        setCombobox();
        this.rentalFee = this.getController().calculateRentalFee(this.rentalTime, this.bike);
        lbBarcode.setText(this.bike.getBarcode());
        lbRentalTime.setText(this.rentalTime);
        lbRentalFees.setText(Utils.getCurrencyFormat(this.rentalFee));
        if (this.bike.getType().equals("Standard e-bike")) {
            ElectricBike eBike = (ElectricBike) this.bike;
            pin.setVisible(true);
            lbPin.setText(eBike.getPin() + "%");
            licensePlate.setVisible(true);
            lbLicensePlate.setText(eBike.getLicensePlate());
        }
    }

    private void setCombobox() throws SQLException {
        List<Dock> listDock = DockDAL.getInstance().getDockList();

        ObservableList<String> listValue = FXCollections.observableArrayList();
        for (Dock dock : listDock) {
            listValue.add(dock.getDockName());
        }
        cbReturnDock.setItems(listValue);
    }

    public void returnBike(String dockReturn, String timeRental) throws IOException, SQLException {
        Map<String, String> result = this.getController().returnBike(dockReturn, timeRental, this.bike);
        if (result.get("RESULT").equals("NOT AVAILABLE")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(result.get("RESULT"));
            alert.setContentText(result.get("MESSAGE"));
            alert.showAndWait();
        } else if (result.get("RESULT").equals("PAYMENT SUCCESSFUL!")) {
            // popup notify pay deposit is successful
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(result.get("RESULT"));
            alert.setContentText(result.get("MESSAGE"));
            alert.showAndWait();

            InvoiceScreenHandler invoiceScreen = new InvoiceScreenHandler(this.stage, Configs.INVOICE_SCREEN_PATH);
            invoiceScreen.setScreenTitle("Invoice Screen");
            invoiceScreen.setController(this.getController());
            invoiceScreen.setInfo();
            invoiceScreen.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(result.get("RESULT"));
            alert.setContentText(result.get("MESSAGE"));
            alert.showAndWait();
        }
    }

    public ReturnBikeController getController() {
        return (ReturnBikeController) super.getController();
    }
}
