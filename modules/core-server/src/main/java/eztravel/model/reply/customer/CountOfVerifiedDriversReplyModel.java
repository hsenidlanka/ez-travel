package eztravel.model.reply.customer;

import eztravel.model.reply.ReplyModel;

/**
 * Created by Menuka on 12/27/17.
 */
public class CountOfVerifiedDriversReplyModel extends ReplyModel {
    private int countVerifiedDrivers;

    public int getCountVerifiedDrivers() {
        return countVerifiedDrivers;
    }

    public void setCountVerifiedDrivers(int countVerifiedDrivers) {
        this.countVerifiedDrivers = countVerifiedDrivers;
    }
}
