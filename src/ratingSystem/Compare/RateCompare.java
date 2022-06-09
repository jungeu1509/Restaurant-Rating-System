package ratingSystem.Compare;

import java.util.Comparator;
import ratingSystem.DTO.RestaurantDTO;

public class RateCompare implements Comparator<RestaurantDTO> {

    @Override
    public int compare(RestaurantDTO o1, RestaurantDTO o2) {
        if(o1.getRate() < o2.getRate()) return 1;
        else if(o1.getRate() > o2.getRate()) return -1;

        if(o1.getIndex() > o2.getIndex()) return 1;
        else if(o1.getIndex() < o2.getIndex()) return -1;
        return 0;
    }
}
