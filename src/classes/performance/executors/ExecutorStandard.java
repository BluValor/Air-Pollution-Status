package classes.performance.executors;

import classes.RunSettings;
import classes.performance.strategies.*;

public class ExecutorStandard extends ExecutorTemplate {

    public ExecutorStandard() {
    }

    @Override
    void initialize() {
        if (RunSettings.getInstance().getStations() != null) {
            BrowsingContext bc1 = new BrowsingContext();
            bc1.setBrowsingStrategy(new BrowsingStationNames());
            this.browsers.add(bc1);
        }
        PutOutContext poc1 = new PutOutContext();
        poc1.setPutOutStrategy(new PutOutStandard());
        this.outputs.add(poc1);
    }
}
