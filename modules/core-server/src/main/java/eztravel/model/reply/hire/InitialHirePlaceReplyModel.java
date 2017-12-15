package eztravel.model.reply.hire;

import eztravel.model.reply.ReplyModel;

/**
 * @version 1.0
 * @auther Vidushka
 */
public class InitialHirePlaceReplyModel extends ReplyModel {
    private boolean isInitialHirePlaceSuccess;

    public boolean getIsInitialHirePlaceSuccess() {
        return isInitialHirePlaceSuccess;
    }

    public void setIsInitialHirePlaceSuccess(boolean initialHirePlaceSuccess) {
        isInitialHirePlaceSuccess = initialHirePlaceSuccess;
    }
}
