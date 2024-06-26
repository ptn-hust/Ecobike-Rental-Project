package group13.ecobikerental.views.rentbike;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.DAL.bike.BikeDAL;
import group13.ecobikerental.controller.RentBikeController;
import group13.ecobikerental.entity.bike.Bike;
import group13.ecobikerental.service.bike.BikeService;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.bike.BikeInfoScreenHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The screen handler for renting a bike form.
 */
public class RentBikeFormScreenHandler extends BaseScreenHandler implements Initializable {
	public TextField tfDockId;
	public TextField tfBarcode;

	public Button btnViewBike;
	public Button btnBack;

	public RentBikeFormScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new RentBikeController(new BikeService(new BikeDAL())));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnViewBike.setOnAction(event -> {
			System.out.println("Clickef: View Bike");
			try {
				int dockId = Integer.parseInt(tfDockId.getText());
				String barcode = tfBarcode.getText();
				System.out.println("test : " + dockId + " and " + barcode);
				try {
					enterBarcodeHandler(dockId, barcode);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Invalid bike ID");
				alert.setContentText("Bike ID should be an integer");
				alert.showAndWait();

			}
		});

		btnBack.setOnMouseClicked(mouseEvent -> {
			System.out.println("Clicked: Back");
			this.getPrev().show();
		});
	}

	/**
     * Displays bike information based on the provided dock ID and bike barcode.
     * @param dockId  The ID of the dock.
     * @param barcode The barcode of the bike.
	 * @throws SQLException 
     */
	private void enterBarcodeHandler(int dockId, String barcode) throws SQLException {
		String bikeCode = null;
		Bike bike = null;
		bikeCode = this.getController().getBikeCodeRequest(barcode);
		if (bikeCode == null) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Invalid bike code");
			alert.setContentText("Please enter barcode again!!");
			alert.showAndWait();
			return ;
		}

		try {
			bike = this.getController().getBikeRequest(dockId, bikeCode);
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Bike not found");
			alert.setContentText("Please enter information again!!");
			alert.showAndWait();
			e.printStackTrace();
			return ;
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
     * Returns the RentBikeController associated with this screen.
     * @return The RentBikeController instance.
     */
	public RentBikeController getController() {
		return (RentBikeController) super.getController();
	}

}
