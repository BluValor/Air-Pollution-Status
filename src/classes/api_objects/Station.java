package classes.api_objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Station implements Serializable {

    public String id, stationName;
    public City city;

    public LinkedList<Sensor> sensors;

    public AQIndex aQIndex;

    public Station(String id, String stationName, City city, LinkedList<Sensor> sensors, AQIndex aQIndex) {
        this.id = id;
        this.stationName = stationName;
        this.city = city;
        this.sensors = sensors;
        this.aQIndex = aQIndex;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Station: id - ").append(this.id).append(", name - ").append(this.stationName)
                .append(", city: (").append(this.city.toString()).append(")");
//        for (Sensor s : sensors) {
//            result.append(System.lineSeparator()).append("    ").append(s);
//        }
        result.append(System.lineSeparator()).append(aQIndex);
        return result.toString();
    }
}
