package corelogic.repository.hire.implementation;

import corelogic.domain.hire.GoogleMapApiReplyModel;
import corelogic.repository.hire.Repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation class for Location related Database activities.
 * This use for autowire
 *
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class LocationImpl implements LocationRepository {

    @Value("${google.map.api.key}")
    private String googleApiKey;

    @Value("${google.map.api.url}")
    private String googleMapUrl;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * This method is responsible for updating driver current location
     *
     * @param email     - driver's email
     * @param longitude - driver's current longitude
     * @param latitude  - driver's current latitude
     * @return - boolean which contains whether update success or not
     */
    @Override
    public boolean updateDriverCurrentLocation(String email, float longitude, float latitude) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);


        try {
            String sqlForGetDriverId = "SELECT driver_id FROM driver WHERE email = ?";
            Object[] args = new Object[]{email};

            String driverId = jdbcTemplate.queryForObject(sqlForGetDriverId, args, String.class);

            String sqlForUpdateDriverLocation = "UPDATE driver_current_location set longitude = ?,latitude = ?, update_time = NOW() WHERE driver_id = ?";

            Object[] argsForLocationUpdate = new Object[]{longitude, latitude, Integer.parseInt(driverId)};

            jdbcTemplate.update(sqlForUpdateDriverLocation, argsForLocationUpdate);

            transactionManager.commit(status);
            return true;

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
            transactionManager.rollback(status);
        }

        return false;
    }

    @Override
    public String giveAddressOfGivenCordinates(double latitude, double longitude) {
        String fullGoogleMapApiUrl = new StringBuilder()
                .append(googleMapUrl)
                .append(latitude)
                .append(",")
                .append(longitude)
                .append("&key=")
                .append(googleApiKey)
                .toString();

        RestTemplate restTemplate = new RestTemplate();

//        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
//        headers.add("Content-Type", "application/json");
//        HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonObject, headers);

        GoogleMapApiReplyModel apiReplyModel = restTemplate.getForObject(fullGoogleMapApiUrl, GoogleMapApiReplyModel.class);

//        System.out.println();
//        System.out.println(apiReplyModel.getReply().toString());;

        return apiReplyModel.getResults().get(0).get("formatted_address").toString();
    }
}
