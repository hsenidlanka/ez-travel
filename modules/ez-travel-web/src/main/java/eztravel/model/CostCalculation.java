package eztravel.model;

import java.io.Serializable;

/**
 * Created by Vidu
 *
 * Map the ajax request's post data of cost calculation and signup for book
 */
public class CostCalculation implements Serializable {
    private String length;
    private String vehicleType;
    private String pickup;
    private String drop;

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

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDrop() {
        return drop;
    }

    public void setDrop(String drop) {
        this.drop = drop;
    }
}
