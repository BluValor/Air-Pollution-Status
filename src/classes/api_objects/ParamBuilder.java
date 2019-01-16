package classes.api_objects;

public class ParamBuilder {

    public String paramName, paramFormula, paramCode, idParam;

    public ParamBuilder() {
    }

    public ParamBuilder setParamName(String paramName) {
        this.paramName = paramName;
        return this;
    }

    public ParamBuilder setParamFormula(String paramFormula) {
        this.paramFormula = paramFormula;
        return this;
    }

    public ParamBuilder setParamCode(String paramCode) {
        this.paramCode = paramCode;
        return this;
    }
    
    public ParamBuilder setIdParam(String idParam) {
        this.idParam = idParam;
        return this;
    }
    
    public Param createParam() {
        return new Param(this.paramName, this.paramFormula, this.paramCode, this.idParam);
    }
}
