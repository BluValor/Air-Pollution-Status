package classes.api_objects;

public class DataBuilder {
    
    public String key;
    public Values values;

    public DataBuilder() {
    }

    public DataBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public DataBuilder setValues(Values values) {
        this.values = values;
        return this;
    }

    public Data createData() {
        return new Data(this.key, this.values);
    }
}
