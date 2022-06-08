package ratingSystem.Repository;

import java.time.LocalDate;

public class RestaurantDAO {

    Integer index;
    String name;
    String menu;
    Integer price;
    Integer rate;
    LocalDate createdAt;
    LocalDate updatedAt;

    public RestaurantDAO(Integer index, String name, String menu, Integer price, Integer rate,
        LocalDate createdAt, LocalDate updatedAt) {
        this.index = index;
        this.name = name;
        this.menu = menu;
        this.price = price;
        this.rate = rate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RestaurantDAO(String[] str) {
        this.index = Integer.parseInt(str[0]);
        this.name = str[1];
        this.menu = str[2];
        this.price = Integer.parseInt(str[3]);
        this.rate = Integer.parseInt(str[4]);
        this.createdAt = LocalDate.parse(str[5]);
        this.updatedAt = LocalDate.parse(str[6]);
    }

    public String toString(char regex) {
        String str = "";
        str += index.toString() + regex;
        str += name + regex;
        str += menu + regex;
        str += price.toString() + regex;
        str += rate.toString() + regex;
        str += createdAt.toString() + regex;
        str += updatedAt.toString();
        return str;
    }
}
