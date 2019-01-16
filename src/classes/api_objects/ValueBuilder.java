package classes.api_objects;

public class ValueBuilder {

    public String date, value;

    public ValueBuilder() {
    }

    public ValueBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public ValueBuilder setValue(String value) {
        this.value = value;
        return this;
    }

    public Value createValue() {
        return new Value(this.date, this.value);
    }
}
