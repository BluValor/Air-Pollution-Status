package classes.performance.executors;

import classes.RunSettings;
import classes.performance.strategies.*;

public class ExecutorAverage extends ExecutorTemplate {
    @Override
    void initialize() {
        if (RunSettings.getInstance().getStations() != null) {
            BrowsingContext bc1 = new BrowsingContext();
            bc1.setBrowsingStrategy(new BrowsingStationNames());
            this.browsers.add(bc1);
        }
        PutOutContext poc1 = new PutOutContext();
        poc1.setPutOutStrategy(new PutOutAverage());
        this.outputs.add(poc1);
    }
}
