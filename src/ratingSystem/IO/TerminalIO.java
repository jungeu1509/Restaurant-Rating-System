package ratingSystem.IO;

import java.util.Scanner;

public class TerminalIO extends IOClass {

    static Scanner in;

    public TerminalIO() {
        in = new Scanner(System.in);
    }

    @Override
    public void close() {
    }

    @Override
    public void print2User(String str) {
        System.out.print(str);
    }

    @Override
    public void println2User(String str) {
        System.out.println(str);
    }

    @Override
    public void printMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("\tRestaurant Rating System");
        System.out.println("\t\tmain menu!");
        System.out.println("--------------------------------------------");
        System.out.println("1. Show all Restaurant");
        System.out.println("2. Search Restaurant");
        System.out.println("3. Create Restaurant");
        System.out.println("4. Modify Restaurant");
        System.out.println("5. Delete Restaurant");
        System.out.println("6. quit program");
        System.out.println("--------------------------------------------");
    }

    @Override
    public int getInt(String show) {
        while (true) {
            try {
                System.out.print("-> Input "+ show + " : ");
                int input = in.nextInt();
                in.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("Error Occured! Please try again. : " + e);
            }
        }
    }

    @Override
    public String getStr(String show) {
        String str;
        while (true) {
            System.out.println("if you want to quit? input quit");
            System.out.println("-> Input "+ show + " : ");
            str = in.nextLine();
            if (str.equals("quit")) {
                System.out.println("cancel");
                return new String("");
            } else if (checkEmpty(str)) {
				System.out.println("Please input name again");
			} else {
				break;
			}
        }
        return str;
    }

    private boolean checkEmpty(String str) {
		if (str.length() == 0) {
			return true;
		}
        return false;
    }
}
