package corelogic.domain.hire;

import org.json.simple.JSONObject;

import java.util.List;

/**
 * Created by Menuka on 12/19/17.
 */
public class GoogleMapApiReplyModel {
    private List<JSONObject> results;

    public List<JSONObject> getResults() {
        return results;
    }

    public void setResults(List<JSONObject> results) {
        this.results = results;
    }
}
