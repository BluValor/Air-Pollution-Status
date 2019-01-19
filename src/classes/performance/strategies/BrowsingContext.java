package classes.performance.strategies;

import classes.api_objects.Station;

import java.util.LinkedList;

public class BrowsingContext {

    private BrowsingStrategy strategy;

    public void setBrowsingStrategy(BrowsingStrategy strategy) {
        this.strategy = strategy;
    }

    public LinkedList<Station> Browse(LinkedList<Station> stations) {
        return strategy.searchData(stations);
    }
}
