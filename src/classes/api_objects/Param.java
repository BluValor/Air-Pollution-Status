package classes.api_objects;

import java.io.Serializable;

public class Param implements Serializable {

    public String paramName, paramFormula, paramCode, idParam;

    public Param(String paramName, String paramFormula, String paramCode, String idParam) {
        this.paramName = paramName;
        this.paramFormula = paramFormula;
        this.paramCode = paramCode;
        this.idParam = idParam;
    }

    @Override
    public String toString() {
        return "Parameter: paramName - " + this.paramName + ", paramFormula - " + this.paramFormula
                + ", paramCode - " + this.paramCode + ", idParam - " + this.idParam;
    }
}
