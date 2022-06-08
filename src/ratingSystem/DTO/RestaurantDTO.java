package ratingSystem.DTO;

import java.time.LocalDate;

public class RestaurantDTO {

    Long index;
    String name;
    String menu;
    Integer price;
    Integer rate;
    LocalDate createdAt;
    LocalDate updatedAt;

    public RestaurantDTO(String name, String menu, Integer price, Integer rate) {
        this.index = 0L;
        this.name = name;
        this.menu = menu;
        this.price = price;
        this.rate = rate;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public RestaurantDTO(Long index, String name, String menu, Integer price, Integer rate,
        LocalDate createdAt, LocalDate updatedAt) {
        this(name, menu, price, rate);
        this.index = index;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public RestaurantDTO(String[] str) {
        this.index = Long.parseLong(str[0]);
        this.name = str[1];
        this.menu = str[2];
        this.price = Integer.parseInt(str[3]);
        this.rate = Integer.parseInt(str[4]);
        this.createdAt = LocalDate.parse(str[5]);
        this.updatedAt = LocalDate.parse(str[6]);
    }

    public void setIndex(Long i) {
        this.index = i;
    }


    public Long getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getMenu() {
        return menu;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getRate() {
        return rate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
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
