package classes.performance.strategies;

import classes.RunSettings;
import classes.api_objects.Station;

import java.util.LinkedList;

public class BrowsingStationNames implements BrowsingStrategy {
    @Override
    public LinkedList<Station> searchData(LinkedList<Station> stations) {
        LinkedList<Station> result = new LinkedList<>();
        for (Station station : stations) {
            for (String name : RunSettings.getInstance().getStations())
                if (station.stationName.equalsIgnoreCase(name))
                    result.add(station);
        }
        return result;
    }
}
