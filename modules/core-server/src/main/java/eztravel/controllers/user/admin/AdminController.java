package eztravel.controllers.user.admin;

import corelogic.repository.feedback.implementation.FeedbackImpl;
import eztravel.model.admin.CustomerFeedbackReviewRequestModel;
import eztravel.model.admin.DriverFeedbackReviewRequestModel;
import eztravel.model.reply.admin.CustomerFeedbackReviewReplyModel;
import eztravel.model.reply.admin.DriverFeedbackReviewReplyModel;
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
}
