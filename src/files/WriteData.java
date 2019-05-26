package files;

import javafx.collections.ObservableList;
import models.Worker;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class WriteData {
    public static boolean writeWorker(String path, Worker worker) {
        OutputStream outputStream = null;
        URL url = WriteData.class.getResource(path);
        try {
            outputStream = new FileOutputStream(new File(url.toURI()), true);
            String data = worker.getId() +
                    "|" + worker.getFirstName() +
                    "|" + worker.getMiddleName() +
                    "|" + worker.getLastName() +
                    "|" + worker.getLavozim() +
                    "|" + worker.getCreatedTime() + "\n";
            outputStream.write(data.getBytes());
            Worker.workersCurrId++;

            return true;
        } catch (IOException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                return false;
            } catch (NullPointerException e) {
                return false;
            }
        }
    }

    public static boolean writeAllWorkers(ObservableList<Worker> workers, String path) {
        OutputStream outputStream = null;
        URL url = WriteData.class.getResource(path);
        try {
            outputStream = new FileOutputStream(new File(url.toURI()));
        } catch (FileNotFoundException e) {
            return false;
        } catch (URISyntaxException e) {
            return false;
        }
        for (Worker worker : workers) {
            Worker.workersCurrId = worker.getId();
            String data = worker.getId() +
                    "|" + worker.getFirstName() +
                    "|" + worker.getMiddleName() +
                    "|" + worker.getLastName() +
                    "|" + worker.getLavozim() +
                    "|" + worker.getCreatedTime() + "\n";
            try {
                outputStream.write(data.getBytes());
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
