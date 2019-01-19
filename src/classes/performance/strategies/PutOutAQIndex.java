package classes.performance.strategies;

import classes.RunSettings;
import classes.api_objects.ParamIndex;
import classes.api_objects.Station;

import java.util.Arrays;
import java.util.LinkedList;

public class PutOutAQIndex implements PutOutStrategy {
    @Override
    public String putOutData(LinkedList<Station> stations) {
        StringBuilder result = new StringBuilder();
        if (RunSettings.getInstance().getParameters() == null) {
            addAllIndexes(result, stations);
        } else {
            addSpecifiedIndexes(result, stations);
        }
        return result.toString();
    }

    private StringBuilder addAllIndexes(StringBuilder result, LinkedList<Station> stations) {
        for (Station station : stations) {
            result.append(System.lineSeparator()).append(System.lineSeparator()).append(station.city.name).append(", ")
                    .append(station.stationName).append(" (").append(station.id).append(") ")
                    .append(" air quality status: ");
            for (ParamIndex index : station.aQIndex.paramIndexes){
                appendIndex(result, index);
            }
        }
        return result;
    }

    private StringBuilder addSpecifiedIndexes(StringBuilder result, LinkedList<Station> stations) {
        for (Station station : stations) {
            result.append(System.lineSeparator()).append(System.lineSeparator()).append(station.city.name).append(", ")
                    .append(station.stationName).append(" (").append(station.id).append(") ")
                    .append(" air quality status: ");
            for (ParamIndex index : station.aQIndex.paramIndexes) {
                for (String param : RunSettings.getInstance().getParameters()) {
                    if (index.prefix.equalsIgnoreCase(param)) {
                        appendIndex(result, index);
                    }
                }
            }
        }
        return result;
    }

    private void appendIndex(StringBuilder result, ParamIndex index) {
        if (index.indexLevel != null) {
            result.append(System.lineSeparator()).append("    ").append(index.prefix.toUpperCase());
            char[] pad = new char[6 - index.prefix.length()];
            Arrays.fill(pad, ' ');
            result.append(pad).append(index.indexLevel.indexLevelName);
        }
    }
}
