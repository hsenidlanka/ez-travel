package eztravel.model.reply;

/**
 * Created by hsenid on 12/5/17.
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
