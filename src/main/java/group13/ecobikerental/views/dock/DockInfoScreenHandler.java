package group13.ecobikerental.views.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.DAL.bike.BikeDAL;
import group13.ecobikerental.controller.RentBikeController;

import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.service.bike.BikeService;
import group13.ecobikerental.utils.Configs;

import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.bike.BikeInfoScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The screen handler for displaying information about a dock and viewing bikes available at the dock.
 */
public class DockInfoScreenHandler extends BaseScreenHandler implements Initializable {
	public ImageView imgDock;
	public ImageView imgLogo;
	public Label lbName;
	public Label lbAddress;
	public Label lbArea;
	public Label lbQuantity;
	public TextField tfBarcode;
	public Button btnViewBike;
	public Button btnBack;

	private Dock dock;

	/**
     * Constructs a new DockInfoScreenHandler instance.
     * @param stage      The stage to display the screen on.
     * @param screenPath The path to the FXML screen file.
     * @param dock       The dock for which to display information.
     * @throws IOException If an I/O error occurs while loading the screen.
     */
	public DockInfoScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
	}

	/**
     * Sets the information to be displayed on the screen.
     */
	public void setInfo() {
		lbName.setText(this.dock.getDockName());
		lbAddress.setText(this.dock.getAddress());
		lbArea.setText(this.dock.getArea() + " m\u00B2");
		lbQuantity.setText(this.dock.getAvailable_bike() + "/" + this.dock.getTotal_bike());
		setImage(imgDock, this.dock.getImg_url(), "assets/images/dock2.jpg");
		setImage(imgLogo, Configs.LOGO_IMG_PATH);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new RentBikeController(new BikeService(new BikeDAL())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnBack.setOnAction(event -> {
			System.out.println("Clicked: Back");
			this.getPrev().show();
		});

		btnViewBike.setOnAction(event -> {
			try {
				System.out.println("Clicked: View bike");
				enterBarcodeHandle(tfBarcode.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void enterBarcodeHandle(String barcode) throws SQLException {
		String bikeCode = null;
		Bike bike = null;
		bikeCode = this.getController().getBikeCodeRequest(barcode);
		if (bikeCode == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid bike code");
			alert.setContentText("Please enter barcode again!!");
			alert.showAndWait();
			return;
		}

		try {
			bike = this.getController().getBikeRequest(this.dock.getId(), bikeCode);
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Bike not found");
			alert.setContentText("Please enter information again!!");
			alert.showAndWait();
			e.printStackTrace();
			return;
		}

		if (bike == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Barcode doesn't belong to this dock");
			alert.setContentText("Please enter information again!!");
			alert.showAndWait();
		} else {
			System.out.println(bike.toString());
			BikeInfoScreenHandler bikeScreen = null;
			try {
				bikeScreen = new BikeInfoScreenHandler(this.stage, Configs.BIKE_INFO_SCREEN_PATH, bike);
				bikeScreen.setInfo();
				bikeScreen.setController(this.getController());
				bikeScreen.setPrev(this);
				bikeScreen.setScreenTitle("Bike Screen");
				bikeScreen.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return HomeController
	 */
	public RentBikeController getController() {
		return (RentBikeController) super.getController();
	}
}
