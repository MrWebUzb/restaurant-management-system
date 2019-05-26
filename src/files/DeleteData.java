package files;

import javafx.collections.ObservableList;
import models.Worker;

public class DeleteData {
    public static boolean deleteWorker(ObservableList<Worker> workers, Worker w){
        boolean result = false;

        result = workers.remove(w);

        result = result && WriteData.writeAllWorkers(workers, "/database/workers.bin");

        return result;
    }
}
