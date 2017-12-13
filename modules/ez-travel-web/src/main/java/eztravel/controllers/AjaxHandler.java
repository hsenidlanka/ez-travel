package eztravel.controllers;

import eztravel.model.CostCalculation;
import eztravel.model.ResponseMapper;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Vidu on 12/12/2017.
 */

@Controller
public class AjaxHandler {
    private JSONObject json;
    private RestTemplate template;
    private String baseUrl = "http://localhost:50000/api/";
    private ResponseMapper responseMapper = null;


    @PostMapping("/findCost")
    public String getEstimatedCost(@RequestBody CostCalculation costCalculation) {

        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "hire/costoftrip";
        json.put("distance", costCalculation.getDistance());
        json.put("vehicleType", costCalculation.getVehicleType());

        try {
            responseMapper = template.postForObject(url, json, ResponseMapper.class);
        } catch (RestClientException e) {
            return "redirect:settings";
        }

        if (responseMapper.getRequestStatus().equals("updated")) {
            return "redirect:settings";
        } else {
            return "redirect:settings";
        }
    }
}
