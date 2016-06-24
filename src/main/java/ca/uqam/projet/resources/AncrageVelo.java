package ca.uqam.projet.resources;

public class AncrageVelo {

    private float longitude = 0;
    private float latitude = 0;

    public AncrageVelo(String longitude, String latitude) {
        this.longitude = Float.parseFloat(longitude);
        this.latitude = Float.parseFloat(latitude);
    }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "AncrageVelo{" + "longitude=" + longitude + ", latitude=" + latitude + '}';
    }
    
}
