package ratingSystem.IO;

public abstract class IOClass {

	public abstract void printMenu();
	
	public abstract void print2User(String str);
	
	public abstract void println2User(String str);

	public abstract void close();
	
	public abstract String getStr();
	
	public abstract int getInt();
}
