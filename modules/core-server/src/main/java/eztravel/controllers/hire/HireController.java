package eztravel.controllers.hire;

import corelogic.repository.hire.implementation.HireImpl;
import eztravel.model.hire.HireCostCalculateRequestModel;
import eztravel.model.hire.InitialHirePlaceRequestModel;
import eztravel.model.reply.hire.HireCostCalculateReplyModel;
import eztravel.model.reply.hire.InitialHirePlaceReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * This Controller is responsible for Hire related calculations
 *
 * @version 1.0
 * @auther Vidushka
 *
 */
@RestController
@RequestMapping("/hire")
public class HireController {

    @Autowired
    HireImpl hireImpl;


    @PostMapping("/costoftrip")
    public ResponseEntity<HireCostCalculateReplyModel> costOfTripCalculate(@RequestBody HireCostCalculateRequestModel model){

        String cost = String.valueOf(hireImpl.sendCostCalculated(model.getLength(), model.getVehicleType()));
//        System.out.println( "recieve => " + model.getLength() + " " + model.getVehicleType());
        HireCostCalculateReplyModel calculateReplyModel = new HireCostCalculateReplyModel();

        calculateReplyModel.setCost(cost);
        calculateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
        calculateReplyModel.setMessage("Trip calculation success");
        calculateReplyModel.setRequestStatus("success");

        return ResponseEntity.status(HttpStatus.OK).body(calculateReplyModel);

    }

    @PostMapping("/intialplacehire")
    public ResponseEntity<InitialHirePlaceReplyModel> placeInitialHire(@RequestBody InitialHirePlaceRequestModel model){

        boolean isInitialHirePlaceSuccessful = hireImpl.placeHire(model.getCustomer_email(),
                model.getStart_location_latitude(),
                model.getStart_location_longitude(),
                model.getVehicle_type(),
                Date.valueOf(model.getDateTime()));

        InitialHirePlaceReplyModel replyModel = new InitialHirePlaceReplyModel();

        if (isInitialHirePlaceSuccessful){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Intial hire place success");
            replyModel.setIsInitialHirePlaceSuccess(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Initial hire placing failed");
        replyModel.setIsInitialHirePlaceSuccess(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);

    }

}
