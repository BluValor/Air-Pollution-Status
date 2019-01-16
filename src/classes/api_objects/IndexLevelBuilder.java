package classes.api_objects;

public class IndexLevelBuilder {

    public String id, indexLevelName;

    public IndexLevelBuilder() {
    }

    public IndexLevelBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public IndexLevelBuilder setIndexLevelName(String indexLevelName) {
        this.indexLevelName = indexLevelName;
        return this;
    }

    public IndexLevel createIndexLevel() {
        return new IndexLevel(this.id, this.indexLevelName);
    }
}
