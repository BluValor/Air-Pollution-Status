package classes.api_objects;

import java.io.Serializable;

public class Value implements Serializable {

    public String date, value;

    public Value(String date, String value) {
        this.date = date;
        this.value = value;
    }

    @Override
    public String toString() {
        return System.lineSeparator() + "        (date - " + this.date + ", value - " + this.value + ")";
    }

}
