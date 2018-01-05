package eztravel.controllers.hire;

import corelogic.domain.hire.CustomerHireRecord;
import corelogic.domain.hire.DriverHireRecord;
import corelogic.domain.hire.IntialHireModel;
import corelogic.notifications.AndroidPushNotificationsService;
import corelogic.repository.hire.implementation.HireImpl;
import corelogic.repository.hire.implementation.LocationImpl;
import eztravel.model.hire.*;
import eztravel.model.reply.hire.ConfirmHireReplyModel;
import eztravel.model.reply.hire.DriverHireRecordsReplyModel;
import eztravel.model.reply.hire.HireCostCalculateReplyModel;
import eztravel.model.reply.hire.InitialHirePlaceReplyModel;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    LocationImpl locationImpl;

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    private final String TOPIC = "EzTravelHirePush";
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

        try {

            JSONObject body = new JSONObject();
            body.put("to", "/topics/" + TOPIC);
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "Ez-Travel - New Hire!!!");

            notification.put("body", "Start - " + locationImpl.giveAddressOfGivenCordinates(Double.parseDouble(model.getStart_location_latitude())
                    , Double.parseDouble(model.getStart_location_longitude())));

            JSONObject data = new JSONObject();
            data.put("Key-1", "JSA Data 1");
            data.put("Key-2", "JSA Data 2");

            body.put("notification", notification);
            body.put("data", data);
            HttpEntity<String> request = new HttpEntity<>(body.toString());

            CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
            CompletableFuture.allOf(pushNotification).join();
            String firebaseResponse = pushNotification.get();

            if (replyOfInitialHirePlace.getIsHirePlaceSucces()) {
                replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
                replyModel.setRequestStatus("success");
                replyModel.setMessage("Intial hire place success");
                replyModel.setHireData(replyOfInitialHirePlace);

                return ResponseEntity.status(HttpStatus.OK).body(replyModel);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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

    @PostMapping("/driverrecords")
    public ResponseEntity<DriverHireRecordsReplyModel> sendDriverHireRecord(@RequestBody DriverHireRecordsRequestModel model) {

        DriverHireRecordsReplyModel replyModel = new DriverHireRecordsReplyModel();

        try {
            List<DriverHireRecord> records = hireImpl.getDriverHireDetails(model.getDriver_email());

            if (records.isEmpty()) {
                replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
                replyModel.setRequestStatus("failed");
                replyModel.setMessage("Driver hire records send failed");
                replyModel.setFeedbackRecords(records);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
            }

            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Driver hire records send successful");
            replyModel.setFeedbackRecords(records);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);

        } catch (Exception e) {
            System.out.println("Reason => " + e.getMessage());
        }
        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("driver hire records send failed");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }

}
