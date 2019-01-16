package classes.api_objects;

public class ParamIndexBuilder {

    public String prefix, calcDate, sourceDataDate;
    public IndexLevel indexLevel;

    public ParamIndexBuilder() {
    }

    public ParamIndexBuilder setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ParamIndexBuilder setCalcDate(String calcDate) {
        this.calcDate = calcDate;
        return this;
    }

    public ParamIndexBuilder setSourceDataDate(String sourceDataDate) {
        this.sourceDataDate = sourceDataDate;
        return this;
    }

    public ParamIndexBuilder setIndexLevel(IndexLevel indexLevel) {
        this.indexLevel = indexLevel;
        return this;
    }

    public ParamIndex createIndexLevel() {
        return new ParamIndex(this.prefix, this.calcDate, this.indexLevel, this.sourceDataDate);
    }
}
