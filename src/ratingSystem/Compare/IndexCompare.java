package ratingSystem.Compare;

import java.util.Comparator;
import ratingSystem.DTO.RestaurantDTO;

public class IndexCompare implements Comparator<RestaurantDTO> {

    @Override
    public int compare(RestaurantDTO o1, RestaurantDTO o2) {
        if(o1.getIndex() > o2.getIndex()) return 1;
        else if(o1.getIndex() < o2.getIndex()) return -1;
        return 0;
    }
}
