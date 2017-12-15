package eztravel.model.hire;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class InitialHirePlaceRequestModel {
    private String customer_email;
    private String start_location_latitude;
    private String start_location_longitude;
    private String vehicle_type;
    private String dateTime;

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getStart_location_latitude() {
        return start_location_latitude;
    }

    public void setStart_location_latitude(String start_location_latitude) {
        this.start_location_latitude = start_location_latitude;
    }

    public String getStart_location_longitude() {
        return start_location_longitude;
    }

    public void setStart_location_longitude(String start_location_longitude) {
        this.start_location_longitude = start_location_longitude;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

