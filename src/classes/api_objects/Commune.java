package classes.api_objects;

import java.io.Serializable;

public class Commune implements Serializable {

    public String communeName, districtName, provinceName;

    public Commune(String communeName, String districtName, String provinceName) {
        this.communeName = communeName;
        this.districtName = districtName;
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "Commune: name - " + this.communeName + ", district - " + this.districtName + ", province - "
                + this.provinceName;
    }
}
