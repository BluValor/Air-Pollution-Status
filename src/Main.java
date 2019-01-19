import classes.ApiInputParser;
import classes.DataManager;
import classes.RunSettings;
import classes.api_objects.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import classes.exceptions.IncompatibleOptionsExceptions;
import classes.performance.executors.ExecutorAQIndex;
import classes.performance.executors.ExecutorStandard;
import classes.performance.executors.ExecutorTemplate;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import javax.json.Json;
import javax.json.stream.JsonParser;

@Command(
        name = "Air Pollution System",
        description = "Provides specified information about air pollution.",
        mixinStandardHelpOptions = true,
        version = "air-pollution-sys 1.0"
)
public class Main implements Runnable {

    @Option(names = { "-u", "--update" }, description = "update data")
    private boolean update;

    @Option(names = { "-d", "--day" }, description = "days in format: [yyyy-mm-dd], where [specified_day] or" +
            "[starts_day,end_day] are given, separated with ':'", split = ":")
    private String[] days;

    @Option(names = { "-t", "--time" }, description = "hours in one of given formats: [start_hour:end_hour], " +
            "[specific_hour], where hours should be specified as: [hh:mm:ss] or [hh:mm] or [hh] or [h], separated" +
            " with ',')", split = ",")
    private String[] hours;

    @Option(names = { "-s", "--stations" }, description = "names of the stations, separated with ':', put between" +
            " \"\" (example: \"station 1\":\"station 2\":\"station 3\")", split = ":")
    private String[] stations;

    @Option(names = { "-p", "--parameters" }, description = "name of the parameters, separated with ':'", split = ":")
    private String[] parameters;

    @Option(names = { "--average" }, description = "calculates average value of parameter (PARAMETER) between " +
            "start_hour and end_hour (TIME)")
    private boolean average;

    @Option(names = { "--fluctuations" }, description = "for given stations (STATIONS) determins the parameter " +
            "which value fluctuated the most since specific_hour (TIME)")
    private boolean fluctuations;

    @Option(names = { "--smallest" }, description = "for given stations (STATIONS) determins the parameter which " +
            "value was the lowest during specific_hour (TIME) and day (DAY)")
    private boolean smallest;

    @Option(names = { "--top" }, description = "for given stations (STATIONS) determins the top N sensors which " +
            "during specific_hour (TIME) and day (DAY noted exceedance of the pollution norm (sorted increasing in" +
            " order of rising exceedance) (the N number should be positive integer)")
    private Integer top;

    @Option(names = { "--edge-values" }, description = "for given parameter (PARAMETER) return s information  when " +
            "and where its value was the lowest and the highest")
    private boolean edgeValues;

    @Option(names = { "--show" }, description = "for given parameter (PARAMETER) between start_hour and end_hour" +
            "(TIME) and days (DAY) draws a bar graph")
    private boolean show;

    @Option(names = { "--getaq" }, description = "for given parameter (PARAMETER) (if not specified for all) and for" +
            "given stations (STATIONS) (if not specified for all) returns their air quality indexes")
    private boolean getAQ;

    public static void main(String[] args) {
        CommandLine.run(new Main(), args);
    }

    public void run() {
        RunSettings.getInstance().initialize(update, days, hours, stations, parameters, top);
        DataManager.getInstance().initDataCollector(update);
        try {
            ExecutorTemplate executor = getExecutor();
            executor.execute();
        } catch (IncompatibleOptionsExceptions e) {
            System.out.println(e.getMessage());
        }
    }

    public int getCheckSum() {
        int checkSum = 0;
        if (average)      checkSum++;
        if (fluctuations) checkSum++;
        if (smallest)     checkSum++;
        if (top != null)  checkSum++;
        if (edgeValues)   checkSum++;
        if (show)         checkSum++;
        if (getAQ)        checkSum++;
        return checkSum;
    }

    public ExecutorTemplate getExecutor() throws IncompatibleOptionsExceptions{
        int checkum = getCheckSum();
        if (checkum > 1)
            throw new IncompatibleOptionsExceptions("Chosen options are incompatible.");
        else if (checkum == 0)
            return new ExecutorStandard();
        else if (getAQ)
            return new ExecutorAQIndex();
        else return null;
    }
}