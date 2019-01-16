package classes.api_objects;

public class CommuneBuilder {

    private String communeName, districtName, provinceName;

    public CommuneBuilder() {
    }

    public CommuneBuilder setCommuneName(String communeName) {
        this.communeName = communeName;
        return this;
    }

    public CommuneBuilder setDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public CommuneBuilder setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public Commune createCommune() {
        return new Commune(this.communeName, this.districtName, this.provinceName);
    }

}
