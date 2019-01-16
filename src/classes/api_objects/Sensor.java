package classes.api_objects;

import java.io.Serializable;

public class Sensor implements Serializable {

    public String id, stationId;
    public Param param;

    public Data data;

    public Sensor(String id, String stationId, Param param, Data data) {
        this.id = id;
        this.stationId = stationId;
        this.param = param;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Sensor: id - " + this.id + ", stationId - " + this.stationId + ", param: (" + this.param + ")"
                + System.lineSeparator() + this.data;
    }
}
