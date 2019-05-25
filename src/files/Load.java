package files;

import models.Worker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load {
        
    public static ArrayList<Worker> loadWorkers(String path) throws IOException {
        FileReader freader = null;
        freader = new FileReader(path);
        BufferedReader br = new BufferedReader(freader);
        ArrayList<Worker> workers = null;
        
        String s;
        while((s = br.readLine()) != null) {
            System.out.println(s);
            String data[] = s.split("[|]");
            Worker w = new Worker(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4]);
            workers.add(w);
        }
        freader.close();

        return workers;
    }
}
