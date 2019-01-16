package classes.api_objects;

import java.io.Serializable;

public class Data implements Serializable {

    public String key;
    public Values values;

    public Data(String key, Values values) {
        this.key = key;
        this.values = values;
    }

    @Override
    public String toString() {
        return "      Data: " + this.key + ", " + this.values;
    }
}
