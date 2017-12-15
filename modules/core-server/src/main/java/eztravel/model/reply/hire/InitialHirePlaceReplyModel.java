package eztravel.model.reply.hire;

import corelogic.domain.hire.IntialHireModel;
import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply customer initial hire place requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class InitialHirePlaceReplyModel extends ReplyModel {
    private boolean isInitialHirePlaceSuccess;
    private IntialHireModel hireData;

    public boolean getIsInitialHirePlaceSuccess() {
        return isInitialHirePlaceSuccess;
    }

    public void setInitialHirePlaceSuccess(boolean initialHirePlaceSuccess) {
        isInitialHirePlaceSuccess = initialHirePlaceSuccess;
    }

    public IntialHireModel getHireData() {
        return hireData;
    }

    public void setHireData(IntialHireModel hireData) {
        this.hireData = hireData;
    }
}
