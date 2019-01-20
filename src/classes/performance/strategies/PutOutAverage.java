package classes.performance.strategies;

import classes.api_objects.Sensor;
import classes.api_objects.Station;
import classes.api_objects.Value;

import java.util.LinkedList;

public class PutOutAverage implements PutOutStrategy {
    @Override
    public String putOutData(LinkedList<Station> stations) {
        StringBuilder result = new StringBuilder();
        for (Station station : stations) {
            result.append(System.lineSeparator()).append(System.lineSeparator()).append(station.city.name)
                    .append(", ").append(station.stationName).append(" (").append(station.id).append(") ")
                    .append(System.lineSeparator());

            LinkedList<Sensor> sensors = ValueGetter.selectStationSensorsForParams(station);

            for (Sensor sensor : sensors) {
                result.append("  average ")
                        .append(WriteAssistant.fixedLength(sensor.param.paramCode.toUpperCase(), 7));

                LinkedList<Value> values = ValueGetter.sortOutValuesForHours(ValueGetter.selectSensorValuesForDays(sensor));

                result.append(WriteAssistant.allingToChar(String.valueOf(ValueCounter.countAverageValue(values)),
                        '.', 9, 4))
                        .append("   ug/m3").append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
