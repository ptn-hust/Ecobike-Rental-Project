package group13.ecobikerental.views.dock;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import group13.ecobikerental.controller.RentBikeController;
import group13.ecobikerental.controller.ViewDockController;
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
//    private int numberOfBike;

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
//        setImage(imgDock, "assets/images/dock2.jpg");
        setImage(imgDock,this.dock.getImg_url() ,"assets/images/dock2.jpg");
        setImage(imgLogo, Configs.LOGO_IMG_PATH);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setController(new RentBikeController());
        btnBack.setOnAction(event -> {
            this.getPrev().show();
        });

        imgLogo.setOnMouseClicked(mouseEvent -> {
            try {
                this.getHomeScreenHandler().show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnViewBike.setOnAction(event -> {
            viewBike();
        });

    }

    public void viewBike() {
        System.out.println("view bike clickeddddd");
        String barcode = tfBarcode.getText();

        Bike bike = null;
        try {
            bike = this.getController().viewBike(this.dock.getDockName(), barcode);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The bike with barcode " + barcode + " could not found");
            alert.setContentText("Please enter barcode again!!!");
            alert.showAndWait();
        }

        if (bike == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("This Barcode Invalid");
            alert.setContentText("Please enter barcode again!!");
            alert.showAndWait();
        } else {
            BikeInfoScreenHandler bikeScreen = null;
            try {
                bikeScreen = new BikeInfoScreenHandler(this.stage, Configs.BIKE_INFO_SCREEN_PATH);
                bikeScreen.setBike(bike);
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

//    public void setNumberOfBike(int numberOfBike) {
//        this.numberOfBike = numberOfBike;
//    }
}
