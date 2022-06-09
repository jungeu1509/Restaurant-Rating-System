package ratingSystem.Compare;

import java.util.Comparator;
import ratingSystem.DTO.RestaurantDTO;

public class NameCompare implements Comparator<RestaurantDTO> {

    @Override
    public int compare(RestaurantDTO o1, RestaurantDTO o2) {
        int o1Len = o1.getName().length();
        int o2Len = o2.getName().length();
        int min = Math.min(o1Len, o2Len);

        for(int i = 0; i < min; i++) {
            if(o1.getName().charAt(i) > o2.getName().charAt(i)) return 1;
            else if(o1.getName().charAt(i) < o2.getName().charAt(i)) return -1;
        }

        if(o1Len > o2Len) return 1;
        else if(o1Len < o2Len) return -1;

        if(o1.getIndex() > o2.getIndex()) return 1;
        else if(o1.getIndex() < o2.getIndex()) return -1;
        return 0;
    }
}
