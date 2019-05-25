/**
 * Sample Skeleton for 'main.fxml' Controller Class
 */

package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="workersBtn"
    private Button workersBtn; // Value injected by FXMLLoader

    @FXML
    void openWorkersWindow(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/workers/main.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Restaurant Management System");
            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert workersBtn != null : "fx:id=\"workersBtn\" was not injected: check your FXML file 'main.fxml'.";

    }
}