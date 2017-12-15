package eztravel.controllers.hire;

import corelogic.repository.hire.implementation.LocationImpl;
import eztravel.model.hire.DriverLocationUpdateRequestModel;
import eztravel.model.reply.hire.DriverLocationUpdateReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This Controller is responsible for Location related functions
 * For detailed instruction of this api please refer below spreadsheet
 * https://goo.gl/FQcZMw
 *
 * Here I have used ResponseEntity to create Custom server-side responses,
 * so we could control the Http Response
 *
 * @version 1.0
 * @auther Vidushka
 *
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationImpl locationImpl;

    /**
     * This controller method is responsible for driver location updating
     *
     * @param model - DriverLocationUpdateRequestModel model that contains,
     *              data needed for driver's current location update
     * @return - DriverLocationUpdateReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/updatedriverlocation")
    public ResponseEntity<DriverLocationUpdateReplyModel> updateDriverLocation(@RequestBody DriverLocationUpdateRequestModel model){

        boolean isLocationUpdated = locationImpl
                .updateDriverCurrentLocation(model.getEmail(), model.getLongitude(), model.getLatitude());

        DriverLocationUpdateReplyModel replyModel = new DriverLocationUpdateReplyModel();

        if (isLocationUpdated){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Driver location update success");
            replyModel.setLocationUpdated(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Location update failed");
        replyModel.setLocationUpdated(false);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }
}
