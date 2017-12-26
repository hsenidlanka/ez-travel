package eztravel.controllers.user.admin;

import corelogic.repository.feedback.implementation.FeedbackImpl;
import corelogic.repository.user.admin.implementation.AdminImpl;
import eztravel.model.admin.*;
import eztravel.model.reply.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Menuka on 12/18/17.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    FeedbackImpl feedbackImpl;

    @Autowired
    AdminImpl adminImpl;


    @PostMapping("/add")
    public ResponseEntity<AdminRegistrationReplyModel> addAdmin(@RequestBody AddAdminRequestModel model) {
        boolean isAdminAdded = adminImpl.registerAdmin(model.getEmail(), model.getPassword(),
                model.getFirst_name(),
                model.getLast_name(), model.getBirthday(), model.getGender(), model.getNic(),
                model.getContact_number(), model.getSuper_admin_email());

        AdminRegistrationReplyModel replyModel = new AdminRegistrationReplyModel();

        if (isAdminAdded) {
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Admin registration success");
            replyModel.setAdminAddSuccess(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Admin registration failed");
        replyModel.setAdminAddSuccess(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }

    @PostMapping("/ban")
    public ResponseEntity<AdminBanReplyModel> banAdmin(@RequestBody AdminBanRequestModel model) {
        boolean isAdminBanned = adminImpl.banAdmin(model.getSuper_admin_email(), model.getAdmin_id());

        AdminBanReplyModel replyModel = new AdminBanReplyModel();
        if (isAdminBanned) {
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Admin ban successful");
            replyModel.setAdminBanned(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Admin ban successful");
        replyModel.setAdminBanned(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);

    }

    @PostMapping("/reviewcustomerfeedback")
    public ResponseEntity<CustomerFeedbackReviewReplyModel> customerFeedbackReviewed(@RequestBody CustomerFeedbackReviewRequestModel model) {

        boolean isFeedbackReviewed = feedbackImpl
                .customerFeedbackReviewed(model.getFeedback_id(), model.getAdmin_email());

        CustomerFeedbackReviewReplyModel replyModel = new CustomerFeedbackReviewReplyModel();

        if (isFeedbackReviewed) {
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Customer feedback review completed");
            replyModel.setFeedbackReviewSuccessful(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Customer feedback review Failed");
        replyModel.setFeedbackReviewSuccessful(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }

    @PostMapping("/reviewdriverfeedback")
    public ResponseEntity<DriverFeedbackReviewReplyModel> driverFeedbackReviewed(@RequestBody DriverFeedbackReviewRequestModel model) {

        boolean isFeedbackReviewed = feedbackImpl
                .driverFeedbackReviewed(model.getFeedback_id(), model.getAdmin_email());

        DriverFeedbackReviewReplyModel replyModel = new DriverFeedbackReviewReplyModel();

        if (isFeedbackReviewed) {
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Driver feedback review completed");
            replyModel.setFeedbackReviewSuccessful(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Driver feedback review Failed");
        replyModel.setFeedbackReviewSuccessful(false);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }

    @PostMapping("/driverconfirm")
    public ResponseEntity<DriverConfirmReplyModel> confirmDriverStatus(@RequestBody DriverConfirmRequestModel model) {

        boolean isDriverConfirmed = adminImpl.confirmDriver(model.getAdmin_email(), model.getDriver_id());
        DriverConfirmReplyModel replyModel = new DriverConfirmReplyModel();

        if (isDriverConfirmed) {
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("success");
            replyModel.setMessage("Driver Confirm successful");
            replyModel.setDriverConfirmed(true);

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setMessage("Driver confirm failed");
        replyModel.setDriverConfirmed(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);
    }
}
