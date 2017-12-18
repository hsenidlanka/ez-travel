package eztravel.controllers.hire;

import corelogic.repository.feedback.implementation.FeedbackImpl;
import eztravel.model.feedback.AddCustomerFeedbackRequestModel;
import eztravel.model.feedback.AddDriverFeedbackRequestModel;
import eztravel.model.reply.feedback.AddCustomerFeedbackReplyModel;
import eztravel.model.reply.feedback.AddDriverFeedbackReplyModel;
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
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackImpl feedbackImpl;

    @PostMapping("/addcustomerfeedback")
    public ResponseEntity<AddCustomerFeedbackReplyModel> addCustomerFeedback(@RequestBody AddCustomerFeedbackRequestModel model){

        boolean isFeedbackAdded = feedbackImpl.addCustomerFeedback(model.getDescription(),
                model.getCustomer_email(),
                model.getDriver_id(),
                model.getHire_id());

        AddCustomerFeedbackReplyModel replyModel = new AddCustomerFeedbackReplyModel();

        if (isFeedbackAdded){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("succeed!");
            replyModel.setCustomerFeedbackSucceed(true);
            replyModel.setMessage("Customer feedback adding success!");

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setCustomerFeedbackSucceed(false);
        replyModel.setMessage("Customer feedback adding failed!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);

    }

    @PostMapping("/adddriverfeedback")
    public ResponseEntity<AddDriverFeedbackReplyModel> addCustomerFeedback(@RequestBody AddDriverFeedbackRequestModel model){

        boolean isFeedbackAdded = feedbackImpl.addDriverFeedback(model.getDescription(),
                model.getDriver_email(),
                model.getCustomer_id(),
                model.getHire_id());

        AddDriverFeedbackReplyModel replyModel = new AddDriverFeedbackReplyModel();

        if (isFeedbackAdded){
            replyModel.setHttpStatusCode(HttpStatus.NO_CONTENT.value());
            replyModel.setRequestStatus("succeed!");
            replyModel.setDriverFeedbackSucceed(true);
            replyModel.setMessage("Driver feedback adding success!");

            return ResponseEntity.status(HttpStatus.OK).body(replyModel);
        }

        replyModel.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        replyModel.setRequestStatus("failed");
        replyModel.setDriverFeedbackSucceed(false);
        replyModel.setMessage("Driver feedback adding failed!");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyModel);

    }
}
