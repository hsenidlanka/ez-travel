package eztravel.controllers;

import eztravel.model.CostCalculation;
import eztravel.model.HireCostCalculateResponseMapper;
import eztravel.model.customer.Locations;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

/**
 * Created by Vidushka
 *
 * Controller for handel ajax request and response
 */

@Controller
@RequestMapping("/")
public class AjaxHandler {
    private JSONObject json;
    private RestTemplate template;
    private String baseUrl = "http://localhost:50000/api/";
    private HireCostCalculateResponseMapper responseMapper = null;


    /**
     * Calculate the estimated cost based on the vehicle type and distence
     *
     * @param costCalculation Object to map request parameters(distance and vehicle type)
     * @return Estimated cost in Rupees will return a String
     */
    @RequestMapping(method = RequestMethod.POST, value = "hire/findCost")
    @ResponseBody
    public String getEstimatedCost(CostCalculation costCalculation) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String url = baseUrl + "hire/costoftrip";
        json.put("length", costCalculation.getLength());
        json.put("vehicleType", costCalculation.getVehicleType());
        try {
            responseMapper = template.postForObject(url, json, HireCostCalculateResponseMapper.class);
        } catch (RestClientException e) {
            System.out.println("Rest client exception");
        }
        if (responseMapper.getRequestStatus().equals("success")) {
            return responseMapper.getCost();
        } else {
            return "Can't calculate cost at the moment.";
        }
    }

    @PostMapping("signin")
    public String redirectToLoginForHire(CostCalculation costCalculation, HttpSession session) {
        if (session.getAttribute("username") == null || session.getAttribute("username") == "") {
            session.setAttribute("pickup", costCalculation.getPickup());
            session.setAttribute("drop", costCalculation.getDrop());
            session.setAttribute("distance", costCalculation.getLength());
            session.setAttribute("vehicleType", costCalculation.getVehicleType());
            return "redirect:../customer/login";
        } else {
            return "home";
        }
    }

    @GetMapping("customer/defaultLocations")
    public String addDefaultLocations(@ModelAttribute Locations location) {
        return "defaultLocations";
    }
}
