package classes;

import classes.api_objects.City;
import classes.api_objects.Station;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

/** singleton class for keeping all data accessible and easily serializable / deserializalbe **/
public class DataCollector implements Serializable {

    private ConcurrentHashMap<Integer, Station> stations = new ConcurrentHashMap<>();

    private ConcurrentHashMap<Integer, City> cities = new ConcurrentHashMap<>();

    public DataCollector() {

    }

    public ConcurrentHashMap<Integer, Station> getStations() {
        return stations;
    }

    public void setStations(ConcurrentHashMap<Integer, Station> stations) {
        this.stations = stations;
    }

    public ConcurrentHashMap<Integer, City> getCities() {
        return cities;
    }

    public void setCities(ConcurrentHashMap<Integer, City> cities) {
        this.cities = cities;
    }
}
