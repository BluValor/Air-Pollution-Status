package classes.performance.strategies;

import classes.api_objects.Station;

import java.util.LinkedList;

public class PutOutContext {

    private PutOutStrategy strategy;

    public void setPutOutStrategy(PutOutStrategy strategy) {
        this.strategy = strategy;
    }

    public String putOutData(LinkedList<Station> stations) {
        return strategy.putOutData(stations);
    }

}
