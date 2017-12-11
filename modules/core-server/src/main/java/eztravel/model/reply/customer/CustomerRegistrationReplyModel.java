package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerRegistrationReplyModel extends ReplyModel {

    private boolean userCreation;

    public boolean isUserCreation() {
        return userCreation;
    }

    public void setUserCreation(boolean userCreation) {
        this.userCreation = userCreation;
    }
}
