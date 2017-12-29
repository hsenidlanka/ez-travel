package eztravel.controllers;

import eztravel.model.CostCalculation;
import eztravel.model.Hire;
import eztravel.model.HireCostCalculateResponseMapper;
import eztravel.model.InitialHirePlaceAccept;
import eztravel.model.customer.Locations;
import eztravel.util.ServerResponseErrorHandler;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${baseUrl}")
    private String baseUrl;
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
        InitialHirePlaceAccept hirePlaceResponse = null;

        String hireDate = hire.getDate();
        String url = baseUrl + "hire/intialplacehire";


        try {
            java.util.Date utilDate = new SimpleDateFormat("dd-MM-yyyy").parse(hireDate);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            json.put("customer_email", session.getAttribute("username"));
            json.put("vehicle_type", hire.getVehicleType());
            json.put("start_location_latitude", hire.getPickupLat());
            json.put("date", sqlDate);
            json.put("time", hire.getTime());
            json.put("start_location_longitude", hire.getPickupLng());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            hirePlaceResponse = template.postForObject(url, json, InitialHirePlaceAccept.class);
        } catch (RestClientException e) {
            System.out.println("Rest client exception");
        }
        if (hirePlaceResponse.getRequestStatus().equals("success")) {
            return "Your hire is placed successfully. Please wait." +
                    " We will confirm with dirver's details.";
        } else {
            return "Can't calculate cost at the moment.";
        }
    }
}
