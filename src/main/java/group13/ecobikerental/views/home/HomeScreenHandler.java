package group13.ecobikerental.views.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.RentBikeController;
import group13.ecobikerental.controller.ViewInfoController;
import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.dock.DockInfoScreenHandler;
import group13.ecobikerental.views.rent_bike.RentBikeFormScreenHandler;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The screen handler for the home screen, where users can view and interact with docks and bikes.
 */
public class HomeScreenHandler extends BaseScreenHandler implements Initializable {
	@FXML
//	public ChoiceBox cbSort;
	public ImageView imgLogo;
	public TextField tfSearch;
	public Button btnSearch;
	public Button btnReload;
	public Button btnRentBike;

	public TableView<Dock> tvDockList;

	public TableColumn<Dock, Integer> colId;

	public TableColumn<Dock, String> colDockName;

	public TableColumn<Dock, String> colAddress;

	public TableColumn<Dock, Integer> colArea;

	public TableColumn<Dock, Integer> colQuantity;

	public TableColumn<Dock, Integer> colAvailableBike;

	/**
     * Constructs a new HomeScreenHandler instance.
     * @param stage      The stage to display the screen on.
     * @param screenPath The path to the FXML screen file.
     * @throws IOException If an I/O error occurs while loading the screen.
     */
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new ViewInfoController());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(imgLogo, Configs.LOGO_IMG_PATH);

		try {
			insertTable(this.getController().getDockListRequest());
		} catch (SQLException e) {
			e.printStackTrace();
		}
//
//		imgLogo.setOnMouseClicked(mouseEvent -> {
//			try {
//				this.getHomeScreenHandler().show();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});

		btnReload.setOnAction(event -> {
			try {
				this.getHomeScreenHandler().show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		btnSearch.setOnAction(event -> {
			String name = tfSearch.getText();
			System.out.println("Search name: " + name);
			try {
				insertTable(getController().searchDockRequest(name));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
		btnRentBike.setOnMouseClicked(mouseEvent -> {			
			try {
				RentBikeFormScreenHandler rentBikeFormScreen = new RentBikeFormScreenHandler(this.stage, Configs.RENT_BIKE_FORM_PATH);
				rentBikeFormScreen.setScreenTitle("Search bike");
				try {
					rentBikeFormScreen.setController(new RentBikeController());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rentBikeFormScreen.setPrev(this);
				rentBikeFormScreen.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		});

		// change to Dock Info Screen if double click on row of table
		tvDockList.setRowFactory(tv -> {
			TableRow<Dock> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					Dock rowData = row.getItem();
					System.out.println("Double click on: " + rowData.getDockName());
					DockInfoScreenHandler dockInfoScreenHandler;
					try {
						dockInfoScreenHandler = new DockInfoScreenHandler(this.stage, Configs.DOCK_INFO_SCREEN_PATH,
								rowData);
						dockInfoScreenHandler.setInfo();
						dockInfoScreenHandler.setPrev(this);
						dockInfoScreenHandler.setScreenTitle("Dock Information");
						dockInfoScreenHandler.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			return row;
		});
	}
	
	

	/**
     * Inserts data into the table.
     * @param listDock The list of docks from the database.
     */
	private void insertTable(final List<Dock> listDock) {
		ObservableList<Dock> list = FXCollections.observableArrayList(listDock);
		colId.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("id"));
		colDockName.setCellValueFactory(new PropertyValueFactory<Dock, String>("dockName"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Dock, String>("address"));
		colArea.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("area"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("total_bike"));
		colAvailableBike.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("available_bike"));
		tvDockList.setItems(list);
	}

	/**
     * Gets the associated controller for this screen handler.
     * @return The {@link ViewInfoController} associated with this screen handler.
     */
	public ViewInfoController getController() {
		return (ViewInfoController) super.getController();
	}

}
