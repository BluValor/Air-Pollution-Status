package classes.performance.executors;

import classes.DataManager;
import classes.api_objects.Station;
import classes.performance.strategies.BrowsingContext;
import classes.performance.strategies.PutOutContext;

import java.util.LinkedList;
import java.util.List;

public abstract class ExecutorTemplate {

    public List<BrowsingContext> browsers = new LinkedList<>();
    public List<PutOutContext> outputs = new LinkedList<>();
    public LinkedList<Station> stations = DataManager.getInstance().getListOfStations();

    abstract void initialize();

    void perform() {
        for (BrowsingContext context : this.browsers)
            this.stations = context.Browse(DataManager.getInstance().getListOfStations());
    }

    void putOut() {
        for (PutOutContext context : this.outputs) {
            System.out.println(System.lineSeparator() + context.putOutData(this.stations));
        }
    }

    public final void execute() {
        initialize();
        perform();
        putOut();
    }
}
