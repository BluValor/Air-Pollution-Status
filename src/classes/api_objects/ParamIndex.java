package classes.api_objects;

import java.io.Serializable;

public class ParamIndex implements Serializable {
    
    public String prefix, calcDate, sourceDataDate;
    public IndexLevel indexLevel;

    public ParamIndex(String prefix, String calcDate, IndexLevel indexLevel, String sourceDataDate) {
        this.prefix = prefix;
        this.calcDate = calcDate;
        this.indexLevel = indexLevel;
        this.sourceDataDate = sourceDataDate;
    }

    @Override
    public String toString() {
        return prefix + "CalcDate - " + calcDate + ", (" + prefix + "IndexLevel: " + indexLevel + "), " + prefix + "SourceDataDate - "
                + sourceDataDate;
    }
}
