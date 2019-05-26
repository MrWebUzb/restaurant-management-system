package files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

public class LoadData {

    public static ObservableList<Worker> loadWorkers(String path) throws IOException {
        ObservableList<Worker> workers = FXCollections.observableArrayList();

        InputStream is = LoadData.class.getResourceAsStream(path);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = reader.readLine()) != null) {
            if(!line.equals("")) {
                String[] data = line.split("[|]");
                try {
                    workers.add(new Worker(Integer.parseInt(data[0]),
                                                data[1], data[2], data[3], data[4], data[5]));
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }

        }
        reader.close();

        return workers;
    }
}
