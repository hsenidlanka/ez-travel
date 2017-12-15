package eztravel.model.reply.hire;

import eztravel.model.reply.ReplyModel;

/**
 * This model used to reply hire cost calculation requests
 *
 * @version 1.0
 * @auther Vidushka
 */

public class HireCostCalculateReplyModel extends ReplyModel{
    private String cost;

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
