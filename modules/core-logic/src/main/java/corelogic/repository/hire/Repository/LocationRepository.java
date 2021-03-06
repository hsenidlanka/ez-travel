package corelogic.repository.hire.Repository;

/**
 * Repository class for Location related Database activities.
 *
 * @version 1.0
 * @auther Vidushka
 */
public interface LocationRepository {
    boolean updateDriverCurrentLocation(String email, float longitude, float latitude);

    String giveAddressOfGivenCordinates(double latitude, double longitude);
}
