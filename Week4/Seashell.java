import java.util.*;
import java.io.*;

public class Seashell{
	private File dir; //current directory
	
	public static void main(String[] args){
		new Seashell().go();
	}
	
	public void go(){
		dir = new File (System.getProperty("user.dir"));
	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to seashell!");
		System.out.print("seashell> ");
		while(scanner.hasNext()){
			System.out.print("seashell> ");
			String command = scanner.nextLine();
			Function function;
			String[] line = command.split(" ");

			if(line[0].equals("down") || line[0].equals("d")){
				function = new Function(line[0], this);
				function.execute(new File(dir.getPath()+"/"+line[1]));
			}
			else{
				function = new Function(command, this);
				function.execute(dir);
			}
		}
	}

	public File getCurrentDirectory(){return dir;}
	public void setCurrentDirectory(File dir){this.dir = dir;};
}
