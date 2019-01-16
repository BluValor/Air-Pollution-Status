package classes.api_objects;

import java.io.Serializable;
import java.util.LinkedList;

public class AQIndex implements Serializable {

    public String id;
    public LinkedList<ParamIndex> paramIndexes;

    public AQIndex(String id, LinkedList<ParamIndex> paramIndexes) {
        this.id = id;
        this.paramIndexes = paramIndexes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("    AQIndex: id - ").append(this.id);
        for (ParamIndex i : paramIndexes) {
            result.append(System.lineSeparator()).append("        ").append(i);
        }
        return result.toString();
    }
}
