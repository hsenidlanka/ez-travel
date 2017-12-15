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
    private String date;
    private String time;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

