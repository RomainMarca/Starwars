package fr.wildcodeschool.starwarsapi;

public class PlanetModel {

    private String name;
    private String climate;
    private int diameter;

    public PlanetModel(String name, String climate, int diameter) {
        this.name = name;
        this.climate = climate;
        this.diameter = diameter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public String toString() {
        return "PlanetModel{" +
                "name='" + name + '\'' +
                ", climate='" + climate + '\'' +
                ", diameter=" + diameter +
                '}';
    }
}
