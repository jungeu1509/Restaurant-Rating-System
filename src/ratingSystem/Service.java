package ratingSystem;

import java.util.ArrayList;
import ratingSystem.DTO.RestaurantDTO;
import ratingSystem.IO.IOClass;
import ratingSystem.Repository.FileRepository;

public class Service {

    final FileRepository repository;
    final IOClass io;

    Service(IOClass io, FileRepository repository) {
        this.repository = repository;
        this.io = io;
    }

    public void showAll() {
//        ArrayList<String> dtoArray = repository.showAllByIndex();
        ArrayList<String> dtoArray = repository.showAllByRate();
        if(dtoArray.size() == 0) {
            io.println2User("there is no data");
            return;
        }
        io.println2User("index/name/menu/price/createdAt/updatedAt");
        for(int i = 0; i < dtoArray.size(); i++) {
            io.println2User(dtoArray.get(i));
        }
    }

    public void search() {
        String name = io.getStr("name");
        if (checkEmpty(name)) {
            io.println2User("cancel");
            return;
        }

        ArrayList<String> ret = repository.searchByName(name);
        if(ret.size() == 0) {
            io.println2User("No data please try again");
        } else {
            io.println2User("index/name/menu/price/createdAt/updatedAt");
            for(int i = 0; i < ret.size(); i++) {
                io.println2User(ret.get(i));
            }
        }
    }

    public void create() {
        String name = io.getStr("name");
		if (checkEmpty(name)) {
            io.println2User("cancel");
			return;
		}
        String menu = io.getStr("menu");
		if (checkEmpty(menu)) {
            io.println2User("cancel");
			return;
		}
        Integer price = io.getInt("price");
        Integer rate = io.getInt("rate");

        RestaurantDTO dto = new RestaurantDTO(name, menu, price, rate);

        String ret = repository.create(dto);
        if (checkEmpty(ret)) {
            io.println2User("create fail");
        } else {
            io.println2User("create success");
            io.println2User(ret);
        }
    }

    public void update() {
        Long index = (long) io.getInt("index");
        if(index <= 0) {
            io.println2User("Wrong input please try again");
            return;
        }

        String name;
        String menu;
        int price;
        int rate;

        name = io.getStr("name");
        if (checkEmpty(name)) {
            io.println2User("cancel");
            return;
        }

        menu = io.getStr("menu");
        if (checkEmpty(menu)) {
            io.println2User("cancel");
            return;
        }
        price = io.getInt("price");
        rate = io.getInt("rate");
        if(rate < 0 || rate > 10) {
            io.println2User("Out of order. please input 0 ~ 10");
        }

        String ret = repository.update(index, name, menu, price, rate);
        if (checkEmpty(ret)) {
            io.println2User("update fail");
        } else {
            io.println2User("update success");
            io.println2User(ret);
        }
    }

    public void delete() {
        int index = io.getInt("index");
        if(index <= 0) {
            io.println2User("Wrong input please try again");
            return;
        }
        if(repository.delete((long) index) == index) {
            io.println2User("delete success");
        } else {
            io.println2User("delete fail. please check your data");
        }
    }

    private boolean checkEmpty(String str) {
		if (str.length() == 0) {
			return true;
		}
        return false;
    }


}
