package ratingSystem;

import java.io.IOException;
import ratingSystem.IO.IOClass;
import ratingSystem.IO.TerminalIO;
import ratingSystem.Repository.FileRepository;

public class MainClass {

    static final String fileDir = "C:\\Temp\\";
    static final String fileName = "ratingSystem.txt";
    static FileRepository repository;
    static Service service;
    static IOClass io;

    public static void main(String[] args) throws Exception {
        repository = new FileRepository(fileDir + fileName);
        io = new TerminalIO();
        service = new Service(io, repository);

        run();
    }

    static void run() throws Exception {
        final int maxMenu = 6;
        while (true) {
            // print menu
            io.printMenu();

            // select menu
            int main;
            while (true) {
                main = io.getInt("main menu number");
				if (main > 0 && main <= maxMenu) {
					break;
				}
                io.println2User("Wrong number. please try again");
            }

            switch (main) {
                // 1. Show all Restaurant
                case 1 -> service.showAll();
                // 2. Search Restaurant
                case 2 -> service.search();
                // 3. Create Restaurant
                case 3 -> service.create();
                // 4. Modify Restaurant
                case 4 -> service.update();
                // 5. Delete Restaurant
                case 5 -> service.delete();
                case maxMenu -> {
                    io.println2User("Auto Saved");
                    close();
                    io.println2User("Program over. Have a nice meal~!");
                    return;
                }
            }
        }
    }

    static void close() throws IOException {
        repository.close();
    }

}
