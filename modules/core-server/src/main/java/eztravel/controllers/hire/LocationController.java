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
 * @version 1.0
 * @auther Vidushka
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationImpl locationImpl;

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
