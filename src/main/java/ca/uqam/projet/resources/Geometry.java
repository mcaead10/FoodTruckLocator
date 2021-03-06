package ca.uqam.projet.resources;

public class Geometry {

    private float coordinates[];

    public Geometry() {
        coordinates = new float[2];
    }

    public Geometry(float x, float y) {
        this.coordinates = new  float[] {x,y};
    }
    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Geometry{" + " X=" + coordinates[0] + ", Y=" + coordinates[1] + '}';
    }

}
