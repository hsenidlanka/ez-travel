package corelogic.repository.hire.Repository;

import corelogic.domain.hire.CustomerHireRecord;
import corelogic.domain.hire.IntialHireModel;

import java.sql.Date;
import java.util.List;

/**
 * Repository class for Hire related Database activities.
 *
 * @version 1.0
 * @auther Vidushka
 */
public interface HireRepository {

    double sendCostCalculated(String length, String vehicleType);

    IntialHireModel placeHire(String customer_email,
                              String start_location_latitude,
                              String start_location_longitude,
                              String vehicle_type,
                              Date date,
                              String time);

    boolean confirmHirePlacement(int hire_id,
                                 float end_location_latitude,
                                 float end_location_longitude,
                                 double cost,
                                 int driver_id,
                                 double length);

    List<CustomerHireRecord> getCustomerHireDetails(String customer_email);
}
