package classes.performance.executors;

import classes.RunSettings;
import classes.performance.strategies.BrowsingContext;
import classes.performance.strategies.BrowsingStationNames;
import classes.performance.strategies.PutOutAQIndex;
import classes.performance.strategies.PutOutContext;

public class ExecutorAQIndex extends ExecutorTemplate {

    public ExecutorAQIndex() {
    }

    @Override
    void initialize() {
        if (RunSettings.getInstance().getStations() != null) {
            BrowsingContext bc1 = new BrowsingContext();
            bc1.setBrowsingStrategy(new BrowsingStationNames());
            this.browsers.add(bc1);
        }
        PutOutContext poc1 = new PutOutContext();
        poc1.setPutOutStrategy(new PutOutAQIndex());
        this.outputs.add(poc1);
    }
}
