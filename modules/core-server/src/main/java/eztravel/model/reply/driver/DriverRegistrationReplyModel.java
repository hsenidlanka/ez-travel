package eztravel.model.reply.driver;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply driver registration requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class DriverRegistrationReplyModel extends ReplyModel {

    private boolean userCreation;

    public boolean isUserCreation() {
        return userCreation;
    }

    public void setUserCreation(boolean userCreation) {
        this.userCreation = userCreation;
    }
}
