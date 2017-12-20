package eztravel.controllers.hire;

import corelogic.domain.hire.CustomerHireRecord;
import corelogic.domain.hire.IntialHireModel;
import corelogic.repository.hire.implementation.HireImpl;
import eztravel.model.hire.ConfirmHireRequestModel;
import eztravel.model.hire.CustomerHireRecordsRequestModel;
import eztravel.model.hire.HireCostCalculateRequestModel;
import eztravel.model.hire.InitialHirePlaceRequestModel;
import eztravel.model.reply.hire.ConfirmHireReplyModel;
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
import java.util.List;

/**
 * This Controller is responsible for Hire related calculations
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
@RequestMapping("/hire")
public class HireController {

    @Autowired
    HireImpl hireImpl;


    /**
     * This controller method is responsible for cost of trip calculation.
     *
     * @param model - HireCostCalculateRequestModel model that contains data(length of trip and vehicle type used for trip),
     *             that need to calculate the cost
     * @return - HireCostCalculateReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/costoftrip")
    public ResponseEntity<HireCostCalculateReplyModel> costOfTripCalculate(@RequestBody HireCostCalculateRequestModel model){

        String cost = String.valueOf(hireImpl.sendCostCalculated(model.getLength(), model.getVehicleType()));
        HireCostCalculateReplyModel calculateReplyModel = new HireCostCalculateReplyModel();

        calculateReplyModel.setCost(cost);
        calculateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
        calculateReplyModel.setMessage("Cost calculation success");
        calculateReplyModel.setRequestStatus("success");

        return ResponseEntity.status(HttpStatus.OK).body(calculateReplyModel);

    }

    /**
     * This controller method is responsible for allowing customer to place intial hire request
     *
     * @param model - InitialHirePlaceRequestModel model that data need for intial hire request.
     * @return - InitialHirePlaceReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/intialplacehire")
    public ResponseEntity<InitialHirePlaceReplyModel> placeInitialHire(@RequestBody InitialHirePlaceRequestModel model){

        IntialHireModel replyOfInitialHirePlace = hireImpl.placeHire(model.getCustomer_email(),
                model.getStart_location_latitude(),
                model.getStart_location_longitude(),
                model.getVehicle_type(),
                Date.valueOf(model.getDate()),
                model.getTime());


        InitialHirePlaceReplyModel replyModel = new InitialHirePlaceReplyModel();

        if (replyOfInitialHirePlace.getIsHirePlaceSucces()){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Intial hire place success");
            replyModel.setHireData(replyOfInitialHirePlace);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Initial hire placing failed");
        replyModel.setHireData(replyOfInitialHirePlace);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);

    }

    /**
     * This controller method is responsible for confirming the Hire Placement
     *
     * @param model - ConfirmHireRequestModel model that contains data needed for hire confirm
     * @return - ConfirmHireReplyModel wrapped in ResponseEntity
     */
    @PostMapping("/confirmhireplacement")
    public ResponseEntity<ConfirmHireReplyModel> confirmHirePlacement(@RequestBody ConfirmHireRequestModel model){

        boolean isHirePlaced = hireImpl.confirmHirePlacement(Integer.parseInt(model.getHire_id()),
                Float.parseFloat(model.getEnd_location_latitude()),
                Float.parseFloat(model.getEnd_location_longitude()),
                Double.parseDouble(model.getCost()) ,
                Integer.parseInt(model.getDriver_id()),
                Double.parseDouble(model.getLength()));

        ConfirmHireReplyModel replyModel = new ConfirmHireReplyModel();

        if (isHirePlaced){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Hire placement completed");
            replyModel.setHirePlaceConfirmed(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Hire placement failed");
        replyModel.setHirePlaceConfirmed(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }

    @PostMapping("/customerrecords")
    public List<CustomerHireRecord> sendCustomerHireRecords(@RequestBody CustomerHireRecordsRequestModel model) {

        return hireImpl.getCustomerHireDetails(model.getCustomer_email());
    }
}
