package classes.api_objects;

import java.util.LinkedList;

public class ValuesBuilder {

    public LinkedList<Value> values = new LinkedList<>();

    public ValuesBuilder() {
    }

    public ValuesBuilder addValue(Value value) {
        this.values.addLast(value);
        return this;
    }

    public Values createValues() {
        return new Values(this.values);
    }
}
