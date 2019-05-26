package workers.controllers;

import files.WriteData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Worker;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewWorkerController {

    @FXML
    private TextField workerId;

    @FXML
    private TextField workerName;

    @FXML
    private TextField workerLastName;

    @FXML
    private TextField workerMiddleName;

    @FXML
    private TextField workerLavozim;

    @FXML
    private Button saveBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Label errorLabel;

    @FXML
    void addNewWorker(ActionEvent event) {
        int id = Integer.parseInt(workerId.getText());
        Worker.workersCurrId = id;
        String firstName = workerName.getText();
        String middleName = workerMiddleName.getText();
        String lastName = workerLastName.getText();
        String lavozim = workerLavozim.getText();

        if( firstName.equals("") || middleName.equals("") || lastName.equals("") ) {
            errorLabel.setText("Barcha maydonlar to'ldirilishi zarur!");
        } else {
            Worker w = new Worker(id, firstName, middleName, lastName, lavozim, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            if(WriteData.writeWorker("/database/workers.bin", w)){
                int cId = id + 1;
                workerId.setText(Integer.toString(cId));
                workerName.setText("");
                workerMiddleName.setText("");
                workerLastName.setText("");
                workerLavozim.setText("");
                errorLabel.setText("Ma'lumotlar saqlandi!");
            } else {
                errorLabel.setText("Ma'lumotlarni saqlashda xatolik yuz berdi! Qayta urinib ko'ring!");
            }
        }
    }

    @FXML
    void toBack(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/workers/views/main.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Restaurant Management System");
            stage.setScene(new Scene(root, 1028, 517));
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
    void initialize() {
        int currId = Worker.workersCurrId + 1;
        workerId.setText(Integer.toString(currId));
    }

}