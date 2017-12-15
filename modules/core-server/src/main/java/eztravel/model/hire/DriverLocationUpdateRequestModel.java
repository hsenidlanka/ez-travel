package eztravel.model.hire;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class DriverLocationUpdateRequestModel {
    private String email;
    private float longitude;
    private float latitude;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
