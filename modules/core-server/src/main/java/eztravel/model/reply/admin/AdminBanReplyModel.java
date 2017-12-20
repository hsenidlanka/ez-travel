package eztravel.model.reply.admin;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/20/17.
 */
public class AdminBanReplyModel extends ReplyModel {

    private boolean isAdminBanned;

    public boolean getIsAdminBanned() {
        return isAdminBanned;
    }

    public void setAdminBanned(boolean adminBanned) {
        isAdminBanned = adminBanned;
    }
}
