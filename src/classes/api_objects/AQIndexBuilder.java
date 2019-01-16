package classes.api_objects;

import java.util.LinkedList;

public class AQIndexBuilder {

    public String id;
    public LinkedList<ParamIndex> paramIndexes = new LinkedList<>();

    public AQIndexBuilder() {
    }
    public AQIndexBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public AQIndexBuilder addParamIndex(ParamIndex paramIndex) {
        this.paramIndexes.addLast(paramIndex);
        return this;
    }

    public AQIndex createAQIndex() {
        return new AQIndex(this.id, this.paramIndexes);
    }
}
