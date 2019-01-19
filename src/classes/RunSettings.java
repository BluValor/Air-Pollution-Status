package classes;

public class RunSettings {
    private static RunSettings ourInstance = new RunSettings();

    public static RunSettings getInstance() {
        return ourInstance;
    }

    private boolean update;
    private String[] days;
    private String[] hours;
    private String[] stations;
    private String[] parameters;

    private Integer top;

    private RunSettings() {
    }

    public void initialize(boolean update, String[] days, String[] hours, String[] stations, String[] parameters,
                       Integer top) {
        this.update = update;
        this.days = days;
        this.hours = hours;
        this.stations = stations;
        this.parameters = parameters;
        this.top = top;
    }

    public boolean isUpdate() {
        return update;
    }

    public String[] getDays() {
        return days;
    }

    public String[] getHours() {
        return hours;
    }

    public String[] getStations() {
        return stations;
    }

    public String[] getParameters() {
        return parameters;
    }

    public Integer getTop() {
        return top;
    }
}
