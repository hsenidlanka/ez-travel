package eztravel.controllers.hire;

import corelogic.repository.hire.implementation.HireImpl;
import eztravel.model.hire.HireCostCalculateRequestModel;
import eztravel.model.reply.hire.HireCostCalculateReplyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        System.out.println( "recieve => " + model.getLength() + " " + model.getVehicleType());
        HireCostCalculateReplyModel calculateReplyModel = new HireCostCalculateReplyModel();

        calculateReplyModel.setCost(cost);
        calculateReplyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
        calculateReplyModel.setMessage("Trip calculation success");
        calculateReplyModel.setRequestStatus("success");

        return ResponseEntity.status(HttpStatus.OK).body(calculateReplyModel);

    }
}
