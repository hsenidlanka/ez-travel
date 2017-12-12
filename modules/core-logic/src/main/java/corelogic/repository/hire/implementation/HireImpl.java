package corelogic.repository.hire.implementation;

import corelogic.repository.hire.Repository.HireRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @auther Vidushka
 */
@Repository
public class HireImpl implements HireRepository{

    @Override
    public double sendCostCalculated(String length, String vehicleType) {

        double numOfKm = Double.parseDouble(length);
        double totalCost = 50.0;

        if ((numOfKm - 1) < 1){
            return totalCost;
        }

        double nextLength = numOfKm - 1;

        switch(vehicleType) {
            case "budget" :
                totalCost += nextLength * 35;
                break;
            case "hybrid" :
                totalCost += nextLength * 55;
                break;
            default :
                totalCost += nextLength * 45;
        }

        return totalCost;
    }
}
