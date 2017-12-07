package eztravel.model.reply.customer;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class CustomerPasswordUpdateReplyModel extends ReplyModel {

    private boolean isPasswordUpdated;

    public boolean getIsPasswordUpdated() {
        return isPasswordUpdated;
    }

    public void setIsPasswordUpdated(boolean passwordUpdated) {
        isPasswordUpdated = passwordUpdated;
    }
}
