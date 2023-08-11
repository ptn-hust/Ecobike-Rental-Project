package group13.ecobikerental.views.home;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.RentBikeController;
import group13.ecobikerental.controller.ViewDockController;
import group13.ecobikerental.entity.dock.Dock;
import group13.ecobikerental.utils.Configs;
import group13.ecobikerental.views.BaseScreenHandler;
import group13.ecobikerental.views.dock.DockInfoScreenHandler;
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

public class HomeScreenHandler extends BaseScreenHandler implements Initializable {

	/**
	 * combobox is used to sort data in table.
	 */
	@FXML
	public ChoiceBox cbSort;
	/**
	 * image logo.
	 */
	@FXML
	public ImageView imgLogo;
	/**
	 * text field input data to search.
	 */
	@FXML
	public TextField tfSearch;
	/**
	 * button search.
	 */
	@FXML
	public Button btnSearch;
	/**
	 * table view list of dock.
	 */
	@FXML
	public TableView<Dock> tvDockList;
	/**
	 * column Id in table.
	 */
	@FXML
	public TableColumn<Dock, Integer> colId;
	/**
	 * column Dock Name in table.
	 */
	@FXML
	public TableColumn<Dock, String> colDockName;
	/**
	 * column Address in table.
	 */
	@FXML
	public TableColumn<Dock, String> colAddress;
	/**
	 * column Area in table.
	 */
	@FXML
	public TableColumn<Dock, Integer> colArea;
	/**
	 * column Quantity in table.
	 */
	@FXML
	public TableColumn<Dock, Integer> colQuantity;

	@FXML
	public TableColumn<Dock, Integer> colAvailableBike;

	/**
	 * @param stage      - this stage
	 * @param screenPath - path of home screen in config
	 *
	 * @throws IOException
	 */
	public HomeScreenHandler(Stage stage, String screenPath) throws IOException {
		super(stage, screenPath);
	}

	/**
	 * @param url            -
	 * @param resourceBundle -
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			setController(new ViewDockController());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setImage(imgLogo, Configs.LOGO_IMG_PATH);

		try {
			insertTable(this.getController().getDockList());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		imgLogo.setOnMouseClicked(mouseEvent -> {
			System.out.println("click logo");
			try {
				this.getHomeScreenHandler().show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		btnSearch.setOnAction(event -> {
			System.out.println("clicked");
			String name = tfSearch.getText();
			try {
				insertTable(getController().searchDock(name));
			} catch (SQLException e) {
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
//                        dockInfoScreenHandler.setNumberOfBike(this.getController().getNumberOfBike(rowData.getDockName()));
//						dockInfoScreenHandler.setController(this.getController());
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
	 * @return - HomeController is controller of HomeScreen
	 */
	public ViewDockController getController() {
		return (ViewDockController) super.getController();
	}

	/**
	 * this method insert data into table.
	 *
	 * @param listDock - list of dock in database
	 */
	private void insertTable(final List listDock) {
		ObservableList<Dock> list = FXCollections.observableArrayList(listDock);
		colId.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("id"));
		colDockName.setCellValueFactory(new PropertyValueFactory<Dock, String>("dockName"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Dock, String>("address"));
		colArea.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("area"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("total_bike"));
		colAvailableBike.setCellValueFactory(new PropertyValueFactory<Dock, Integer>("available_bike"));
		tvDockList.setItems(list);
	}

//    public static void main(String[] args) {
//        String barcode = "1234567890098";
//        System.out.println(Utils.md5(barcode));
//    }
}
