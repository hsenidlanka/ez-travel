package eztravel.model;

import java.io.Serializable;

/**
 * Created by Vidu on 13/12/2017.
 */
public class CostCalculation implements Serializable {
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
