package eztravel.model.reply.admin;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/20/17.
 */
public class AdminRegistrationReplyModel extends ReplyModel {
    boolean isAdminAddSuccess;

    public boolean getIsAdminAddSuccess() {
        return isAdminAddSuccess;
    }

    public void setAdminAddSuccess(boolean adminAddSuccess) {
        isAdminAddSuccess = adminAddSuccess;
    }
}
