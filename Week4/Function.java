import java.util.*;
import java.io.*;

public class Function{
	private String f_name;
	private String f_desc;
	private Seashell f_shell;
	
	private final static Map<String, String> DESCRIPTIONS;
	static{
		DESCRIPTIONS = new HashMap<String, String>();
		DESCRIPTIONS.put("list", ": lists the contents of current directory"); 
		DESCRIPTIONS.put("down", " [dir]: moves down to listed directory");
		DESCRIPTIONS.put("up", ": moves to the parent directory");
		DESCRIPTIONS.put("wai", ": prints the current directory");
		DESCRIPTIONS.put("exit", ": exits the shell");
		DESCRIPTIONS.put("help", ": prints a list of supported commands");
	}
		
	
	public Function(String name, Seashell shell){
		f_name = name;
		f_desc = Function.DESCRIPTIONS.get(name);
		f_shell = shell;
	}
	
	public String getName(){return f_name;}
	public String getDesc(){return f_desc;}
	
	public void execute(File dir){ //takes the current directory to execute appropriately
		switch(f_name){
			case "l" : list(); break;
			case "list" :
				list();
				break;
			case "d" : down(dir);break;
			case "down" :
				down(dir);
				break;
			case "u" : up();break;
			case "up" :
				up();
				break;
			case "w" : wai();break;
			case "wai" :
				wai();
				break;
			case "e" : exit();break;
			case "exit" :
				exit();
				break;
			case "h" : help();break;
			case "help" : 
				help();
				break;
			default :
				help();
				break;
		}
	}
	
	//lists files in current directory
	private void list(){
		for(File file : f_shell.getCurrentDirectory().listFiles()){
			if(file.isDirectory())
				System.out.print("(d) ");
			else
				System.out.print("(f) ");
			
			System.out.print(file.getName() +"\n");
		}
	}
	//changes directory to specified directory
	private void down(File dir){f_shell.setCurrentDirectory(dir);}
	//sets shell's directory up one level
	private void up(){f_shell.setCurrentDirectory(new File(f_shell.getCurrentDirectory().getParent()));}
	//prints path to specified directory
	private void wai(){System.out.println(f_shell.getCurrentDirectory().getPath());}
	//exits shell, prints farewell
	private void exit(){
		System.out.println("bye");
		System.exit(0);
	}
	//prints functions and their decriptions
	private void help(){
		for(String func : Function.DESCRIPTIONS.keySet())
			System.out.printf("(%c)"+"%s"+"%s%n", func.charAt(0), func.substring(1,func.length()), Function.DESCRIPTIONS.get(func)); 
	}
}	
