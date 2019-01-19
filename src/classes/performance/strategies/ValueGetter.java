package classes.performance.strategies;

import classes.TimeManager;
import classes.api_objects.Sensor;
import classes.api_objects.Station;
import classes.api_objects.Value;

import java.sql.Time;
import java.util.LinkedList;
import java.util.LinkedList;

public class ValueGetter {

    public static LinkedList<Sensor> getSensorsForMultipleParams(String[] params, Station station) {
        LinkedList<Sensor> result = new LinkedList<>();
        for (String param : params)
            result.addAll(getSensorsForParam(param, station));
        return result;
    }
    
    public static LinkedList<Sensor> getSensorsForParam(String param, Station station) {
        LinkedList<Sensor> result = new LinkedList<>();
        for (Sensor sensor : station.sensors)
            if (sensor.param.paramCode.equalsIgnoreCase(param))
                result.addLast(sensor);
        return result;
    }

    public static LinkedList<Value> getMultipleSensorValuesForSingleDay(String day, LinkedList<Sensor> sensors) {
        LinkedList<Value> result = new LinkedList<>();
        for (Sensor sensor : sensors)
            result.addAll(getSensorValuesForSingleDay(day, sensor));
        return result;
    }

    public static LinkedList<Value> getSensorValuesForSingleDay(String day, Sensor sensor) {
        LinkedList<Value> result = new LinkedList<>();
        for (Value value : sensor.data.values.values) {
            if (TimeManager.getDay(value.date).equals(day))
                result.addLast(value);
        }
        return result;
    }

    public static LinkedList<Value> getMultipleSensorValuesForDaysBetween(String startDay, String endDay, LinkedList<Sensor> sensors) {
        LinkedList<Value> result = new LinkedList<>();
        for (Sensor sensor : sensors)
            result.addAll(getSensorValuesForDaysBetween(startDay, endDay, sensor));
        return result;
    }

    public static LinkedList<Value> getSensorValuesForDaysBetween(String startDay, String endDay, Sensor sensor) {
        LinkedList<Value> result = new LinkedList<>();
        for (Value value : sensor.data.values.values) {
            String dayAfter = TimeManager.nextDay(endDay);
            for (String tmpDay = startDay; !TimeManager.checkDaysEqual(tmpDay, dayAfter); tmpDay = TimeManager.nextDay(tmpDay)) {
                if (TimeManager.getDay(value.date).equals(tmpDay))
                    result.addFirst(value);
            }
        }
        return result;
    }

    public static LinkedList<Value> getAllMultipleSensorValues(LinkedList<Sensor> sensors) {
        LinkedList<Value> result = new LinkedList<>();
        for (Sensor sensor : sensors)
            result.addAll(getAllSensorValues(sensor));
        return result;
    }

    public static LinkedList<Value> getAllSensorValues(Sensor sensor) {
        LinkedList<Value> result = new LinkedList<>();
        result.addAll(sensor.data.values.values);
        return result;
    }

    public static LinkedList<Value> getValuesForSingleHour(LinkedList<Value> values, String hour) {
        LinkedList<Value> result = new LinkedList<>();
        for (Value value : values)
            if (TimeManager.compareHours(TimeManager.getHour(value.date), hour))
                result.addLast(value);
        return result;
    }

    public static LinkedList<Value> getValuesForHoursBetween(LinkedList<Value> values, String startHour, String endHour) {
        LinkedList<Value> result = new LinkedList<>();
        for (Value value : values)
            if (TimeManager.isHourBetween(startHour, endHour, TimeManager.getHour(value.date)))
                result.addLast(value);
        return result;
    }
}
