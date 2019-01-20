package classes.performance.strategies;

import classes.RunSettings;
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
            LinkedList<Sensor> sensors;

            if (RunSettings.getInstance().getParameters() != null)
                sensors = ValueGetter.getSensorsForMultipleParams(RunSettings.getInstance().getParameters(), station);
            else sensors = station.sensors;

            for (Sensor sensor : sensors) {
                LinkedList<Value> values;
                result.append("  ").append(sensor.param.paramCode.toUpperCase()).append(System.lineSeparator());

                if (RunSettings.getInstance().getDays() != null) {
                    if (RunSettings.getInstance().getDays().length == 1)
                        values = ValueGetter.getSensorValuesForSingleDay(RunSettings.getInstance().getDays()[0],
                                sensor);
                    else values = ValueGetter.getSensorValuesForDaysBetween(RunSettings.getInstance().getDays()[0],
                            RunSettings.getInstance().getDays()[1], sensor);
                } else values = ValueGetter.getAllSensorValues(sensor);

                if (RunSettings.getInstance().getHours() != null)
                    if (RunSettings.getInstance().getHours().length == 1)
                        values = ValueGetter.getValuesForSingleHour(values, RunSettings.getInstance().getHours()[0]);
                    else values = ValueGetter.getValuesForHoursBetween(values, RunSettings.getInstance().getHours()[0],
                            RunSettings.getInstance().getHours()[1]);

                for (Value value : values)
                    result.append("    ").append(value.date).append("    ").append(value.value)
                            .append(System.lineSeparator());
            }
        }
        return result.toString();
    }
}
