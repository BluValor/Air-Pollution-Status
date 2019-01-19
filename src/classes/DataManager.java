package classes;

import classes.api_objects.City;
import classes.api_objects.Station;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager {

    private static DataManager ourInstance = new DataManager();

    public static DataManager getInstance() {
        return ourInstance;
    }

    private DataManager() {
    }

    private String DCSerializationPath = "D:\\AGH\\przedmioty\\obiektowe\\air-pollution-sys\\serialized_data.bin";

    private DataCollector dataCollector = null;

    public void initDataCollector(boolean updateData) {
        if (!updateData) {
            loadData();
        } else {
            this.dataCollector = new DataCollector();
            ApiInputParser parser = new ApiInputParser();
            parser.parseStations();
            collectStations(parser);
            collectCities(parser);
            saveData();
        }
    }

    public void saveData() {
        try {
            ObjectOutputStream dataOutputStream = new ObjectOutputStream(new FileOutputStream(DCSerializationPath));
            dataOutputStream.writeObject(dataCollector);
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadData() {
        try {
            ObjectInputStream dataCollectorInputStream = new ObjectInputStream(new FileInputStream(DCSerializationPath));
            this.dataCollector = (DataCollector) dataCollectorInputStream.readObject();
            dataCollectorInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void collectStations(ApiInputParser parser) {
        dataCollector.setStations(parser.getStations());
    }

    public ConcurrentHashMap<Integer, Station> getStations() {
        return dataCollector.getStations();
    }

    public LinkedList<Station> getListOfStations() {
        LinkedList<Station> result = new LinkedList<>();
        for (Map.Entry<Integer, Station> entry : dataCollector.getStations().entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    public void collectCities(ApiInputParser parser) {
        dataCollector.setCities(parser.getCities());
    }

    public ConcurrentHashMap<Integer, City> getCities() {
        return dataCollector.getCities();
    }
}
