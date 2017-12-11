package eztravel.model.hire;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class HireCostCalculateRequestModel {
    private String length;
    private String vehicleType;

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
