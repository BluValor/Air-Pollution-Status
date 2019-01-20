package classes.performance.strategies;

import classes.RunSettings;
import classes.api_objects.Sensor;
import classes.api_objects.Station;
import classes.api_objects.Value;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class PutOutStandard implements PutOutStrategy {
    @Override
    public String putOutData(LinkedList<Station> stations) {
        StringBuilder result = new StringBuilder();
        for (Station station : stations) {
            result.append(System.lineSeparator()).append(System.lineSeparator()).append(station.city.name)
                    .append(", ").append(station.stationName).append(" (").append(station.id).append(") ")
                    .append(System.lineSeparator());

            LinkedList<Sensor> sensors = ValueGetter.selectStationSensorsForParams(station);

            for (Sensor sensor : sensors) {
                result.append("  ").append(sensor.param.paramCode.toUpperCase()).append(System.lineSeparator());

                LinkedList<Value> values = ValueGetter.sortOutValuesForHours(ValueGetter.selectSensorValuesForDays(sensor));

                for (Value value : values)
                    result.append("    ").append(value.date).append("    ").append(value.value)
                            .append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
