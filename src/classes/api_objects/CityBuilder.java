package classes.api_objects;

public class CityBuilder {

    private String id, name;
    private Commune commune;

    public CityBuilder() {
    }

    public CityBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public CityBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CityBuilder setCommune(Commune commune) {
        this.commune = commune;
        return this;
    }

    public City createCity() {
        return new City(this.id, this.name, this.commune);
    }
}
