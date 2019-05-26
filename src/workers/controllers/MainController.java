/**
 * Sample Skeleton for 'main.fxml' Controller Class
 */

package workers.controllers;

import com.sun.prism.impl.Disposer.Record;
import files.DeleteData;
import files.LoadData;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Worker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="workersTable"
    private TableView<Worker> workersTable; // Value injected by FXMLLoader

    @FXML // fx:id="workerId"
    private TableColumn<Worker, Integer> workerId; // Value injected by FXMLLoader

    @FXML // fx:id="workerFirstName"
    private TableColumn<Worker, String> workerFirstName; // Value injected by FXMLLoader

    @FXML // fx:id="workerMiddleName"
    private TableColumn<Worker, String> workerMiddleName; // Value injected by FXMLLoader

    @FXML // fx:id="workerLastName"
    private TableColumn<Worker, String> workerLastName; // Value injected by FXMLLoader

    @FXML // fx:id="workerLavozim"
    private TableColumn<Worker, String> workerLavozim; // Value injected by FXMLLoader

    @FXML // fx:id="workerCreated"
    private TableColumn<Worker, String> workerCreated; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="searchField"
    private TextField searchField; // Value injected by FXMLLoader

    @FXML // fx:id="searchBtn"
    private Button searchBtn; // Value injected by FXMLLoader

    @FXML // fx:id="resetBtn"
    private Button resetBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addWorker"
    private Button addWorker; // Value injected by FXMLLoader


    private ObservableList<Worker> workers, worker;

    @FXML
    void addWorkerAction(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/workers/views/newWorker.fxml"));
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

    @FXML
    void resetAction(ActionEvent event) {
        searchField.setText("");
        workersTable.setItems(workers);
    }

    @FXML
    void backToMain(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/main/main.fxml"));
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

    @FXML
    void searchWorker(ActionEvent event) {
        String workerName = searchField.getText();
        worker = FXCollections.observableArrayList();
        for(Worker w: workers){
            if(w.getFirstName().toLowerCase().indexOf(workerName.toLowerCase()) != -1
                || w.getMiddleName().toLowerCase().indexOf(workerName.toLowerCase()) != -1){
                worker.add(w);
            }
        }

        workersTable.setItems(worker);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            workers = LoadData.loadWorkers("/database/workers.bin");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Null pointer exception on workers data");
        }
        workerId.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("Id"));
        workerFirstName.setCellValueFactory(new PropertyValueFactory<Worker, String>("firstName"));
        workerLastName.setCellValueFactory(new PropertyValueFactory<Worker, String>("lastName"));
        workerMiddleName.setCellValueFactory(new PropertyValueFactory<Worker, String>("middleName"));
        workerLavozim.setCellValueFactory(new PropertyValueFactory<Worker, String>("lavozim"));
        workerCreated.setCellValueFactory(new PropertyValueFactory<Worker, String>("createdTime"));

        //Insert Button
        TableColumn col_action = new TableColumn<>("Action");
        workersTable.getColumns().add(col_action);

        col_action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>,
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_action.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell();
            }

        });

        workersTable.setItems(workers);
    }

    private class ButtonCell extends TableCell<Record, Boolean> {
        final Button cellButton = new Button("Delete");

        ButtonCell(){

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    Worker currentWorker = (Worker) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    //remove selected item from the table list
                    DeleteData.deleteWorker(workers, currentWorker);
                    ButtonCell.this.getTableView().refresh();
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }
}
