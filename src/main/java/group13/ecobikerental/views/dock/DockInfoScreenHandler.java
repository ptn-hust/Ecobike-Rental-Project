package group13.ecobikerental.views.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.ViewInfoController;
import group13.ecobikerental.controller.RentBikeController;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.entity.dock.Dock;
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

	public DockInfoScreenHandler(Stage stage, String screenPath, Dock dock) throws IOException {
		super(stage, screenPath);
		this.dock = dock;
	}

	/**
	 * this method set information of screen.
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
			setController(new ViewInfoController());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnBack.setOnAction(event -> {
			this.getPrev().show();
		});

		btnViewBike.setOnAction(event -> {
			try {
				viewBike(tfBarcode.getText());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}

	public void viewBike(String barcode) throws SQLException {
		RentBikeController newController = new RentBikeController();
		String bikeCode = null;
		Bike bike = null;
		bikeCode = newController.getBikeCodeRequest(barcode);
		if (bikeCode == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid bike code");
			alert.setContentText("Please enter barcode again!!");
			alert.showAndWait();
			return;
		}

		try {
			bike = newController.getBikeRequest2(this.dock.getId(), bikeCode);
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
			BikeInfoScreenHandler bikeScreen = null;
			try {
				bikeScreen = new BikeInfoScreenHandler(this.stage, Configs.BIKE_INFO_SCREEN_PATH, bike);
//				bikeScreen.setBike(bike);
				bikeScreen.setInfo();
				try {
					bikeScreen.setController(new RentBikeController());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	public ViewInfoController getController() {
		return (ViewInfoController) super.getController();
	}
}
