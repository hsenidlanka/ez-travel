package corelogic.repository.hire.Repository;

/**
 * @version 1.0
 * @auther Vidushka
 */
public interface LocationRepository {
    boolean updateDriverCurrentLocation(String email, float longitude, float latitude);
}
