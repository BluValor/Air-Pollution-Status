package classes.performance.strategies;

import classes.api_objects.Station;

import java.util.LinkedList;

public interface PutOutStrategy {

    String putOutData(LinkedList<Station> stations);
}
