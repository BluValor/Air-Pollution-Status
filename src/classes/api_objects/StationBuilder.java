package classes.api_objects;

import java.util.LinkedList;

public class StationBuilder {

    private String id, stationName;
    private City city;

    public LinkedList<Sensor> sensors = new LinkedList<>();

    public AQIndex aQIndex;

    public StationBuilder() {
    }

    public StationBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public StationBuilder setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public StationBuilder setCity(City city) {
        this.city = city;
        return this;
    }

    public StationBuilder addSensor(Sensor sensor) {
        this.sensors.addLast(sensor);
        return this;
    }

    public StationBuilder setAQIndex(AQIndex aQIndex) {
        this.aQIndex = aQIndex;
        return this;
    }

    public Station createStation() {
        return new Station(this.id, this.stationName, this.city, this.sensors, this.aQIndex);
    }
}
