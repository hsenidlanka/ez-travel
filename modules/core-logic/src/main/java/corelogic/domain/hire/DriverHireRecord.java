package corelogic.domain.hire;

import java.sql.Date;

/**
 * Created by Menuka on 1/3/18.
 */
public class DriverHireRecord {
    private int hire_id;
    private int customer_id;
    private String from;
    private String to;
    private String vehicle_type;
    private double length;
    private double cost;
    private Date date;

    public int getHire_id() {
        return hire_id;
    }

    public void setHire_id(int hire_id) {
        this.hire_id = hire_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
