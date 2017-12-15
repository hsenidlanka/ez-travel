package eztravel.model.hire;

/**
 * Created by Menuka on 12/15/17.
 */
public class ConfirmHireRequestModel {
    private String hire_id;
    private String end_location_latitude;
    private String end_location_longitude;
    private String cost;
    private String driver_id;
    private String  length;

    public String getHire_id() {
        return hire_id;
    }

    public void setHire_id(String hire_id) {
        this.hire_id = hire_id;
    }

    public String getEnd_location_latitude() {
        return end_location_latitude;
    }

    public void setEnd_location_latitude(String end_location_latitude) {
        this.end_location_latitude = end_location_latitude;
    }

    public String getEnd_location_longitude() {
        return end_location_longitude;
    }

    public void setEnd_location_longitude(String end_location_longitude) {
        this.end_location_longitude = end_location_longitude;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
