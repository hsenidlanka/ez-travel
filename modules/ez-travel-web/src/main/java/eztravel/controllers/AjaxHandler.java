package eztravel.controllers;

import eztravel.model.CostCalculation;
import eztravel.model.Hire;
import eztravel.model.HireCostCalculateResponseMapper;
import eztravel.model.customer.Locations;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    @PostMapping("placeHire")
    @ResponseBody
    public String placeHire(Hire hire, HttpSession session) {
        json = new JSONObject();
        template = new RestTemplate();
        template.setErrorHandler(new ServerResponseErrorHandler());

        String hireDate = hire.getPickupDate();
        java.util.Date utilDate = null;
        try {
            utilDate = new SimpleDateFormat("MM/dd/yyy").parse(hireDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        String url = baseUrl + "hire/costoftrip";
        json.put("email", session.getAttribute("username"));
        json.put("vehicleType", hire.getVehicleType());
        json.put("pickupLat", hire.getPickupLat());
        json.put("pickupDate", sqlDate);
        json.put("pickupTime", hire.getTime());
        json.put("pickupLng", hire.getPickupLng());
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
}
