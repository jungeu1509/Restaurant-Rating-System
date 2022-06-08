package ratingSystem;

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

    public void close() {
        // TODO Auto-generated method stub

    }

    public void showAll() {
        // TODO Auto-generated method stub

    }

    public void search() {
        // TODO Auto-generated method stub
        String name = io.getStr();
        if (checkEmpty(name)) {
            io.println2User("Please input name longer");
            return;
        }
    }

    public void create() {
        io.print2User("input name : ");
        String name = io.getStr();
		if (checkEmpty(name)) {
			return;
		}

        io.print2User("input menu : ");
        String menu = io.getStr();
		if (checkEmpty(menu)) {
			return;
		}

        io.print2User("input price : ");
        Integer price = io.getInt();

        io.print2User("input rate : ");
        Integer rate = io.getInt();
        RestaurantDTO dto = new RestaurantDTO(name, menu, price, rate);
        String ret = repository.save(dto);
        if (checkEmpty(ret)) {
            io.println2User("create fail");
        } else {
            io.println2User("create success");
            io.println2User(ret);
        }
    }

    public void update() {
        // TODO Auto-generated method stub

    }

    public void delete() {
        // TODO Auto-generated method stub

    }

    private boolean checkEmpty(String str) {
		if (str.length() == 0) {
			return true;
		}
        return false;
    }


}
