package classes.performance.strategies;

import classes.RunSettings;
import classes.api_objects.Sensor;
import classes.api_objects.Station;

import java.util.LinkedList;

public class BrowsingParameters implements BrowsingStrategy {
    @Override
    public LinkedList<Station> searchData(LinkedList<Station> stations) {
        LinkedList<Station> result = new LinkedList<>();
        for (Station station : stations) {
            for (String param : RunSettings.getInstance().getParameters())
                for (Sensor sensor : station.sensors) {
                    if (sensor.param.paramCode.equalsIgnoreCase(param))
                        result.add(station);
                }
        }
        return result;
    }
}
