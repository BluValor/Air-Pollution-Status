package classes.performance.strategies;

import classes.RunSettings;
import classes.api_objects.Station;

import java.util.LinkedList;
import java.util.List;

public interface BrowsingStrategy {

    LinkedList<Station> searchData(LinkedList<Station> stations);
}
