package classes.api_objects;

public class SensorBuilder {

    public String id, stationId;
    public Param param;

    public Data data;

    public SensorBuilder() {
    }

    public SensorBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public SensorBuilder setStationId(String stationId) {
        this.stationId = stationId;
        return this;
    }

    public SensorBuilder setParam(Param param) {
        this.param = param;
        return this;
    }

    public SensorBuilder setData(Data data) {
        this.data = data;
        return this;
    }

    public Sensor createSensor() {
        return new Sensor(this.id, this.stationId, this.param, this.data);
    }
}
