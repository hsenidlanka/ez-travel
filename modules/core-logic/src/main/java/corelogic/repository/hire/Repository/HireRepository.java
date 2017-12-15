package corelogic.repository.hire.Repository;

import java.sql.Date;

/**
 * @version 1.0
 * @auther Vidushka
 */
public interface HireRepository {

    double sendCostCalculated(String length, String vehicleType);

    boolean placeHire(String customer_email,
                      String start_location_latitude,
                      String start_location_longitude,
                      String vehicle_type,
                      Date dateTime);
}
