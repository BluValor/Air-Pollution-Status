package classes.api_objects;

import java.io.Serializable;
import java.util.LinkedList;

public class Values implements Serializable {

    public LinkedList<Value> values;

    public Values() {
    }

    public Values(LinkedList<Value> values) {
        this();
        this.values = values;
    }

    @Override
    public String toString() {
        return "Values: " + values;
    }
}
