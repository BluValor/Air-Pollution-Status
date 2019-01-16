package classes.api_objects;

import java.io.Serializable;

public class IndexLevel implements Serializable {

    public String id, indexLevelName;

    public IndexLevel(String id, String indexLevelName) {
        this.id = id;
        this.indexLevelName = indexLevelName;
    }

    @Override
    public String toString() {
        return "id - " + id + ", indexLevelName - " + indexLevelName;
    }
}
