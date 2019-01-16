package classes.api_objects;

import java.io.Serializable;

public class City implements Serializable {

    public String id, name;
    public Commune commune;

    public City(String id, String name, Commune commune) {
        this.id = id;
        this.name = name;
        this.commune = commune;
    }

    @Override
    public String toString() {
        return "City: id - " + this.id + ", name - " + this.name + ", commune: (" + this.commune.toString() + ")";
    }
}
