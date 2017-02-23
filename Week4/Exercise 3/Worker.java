import java.io.*;
import java.util.*;

public class Worker {
	// can easily be run 'stand alone'...
	public static void main(String args[]) throws Exception {
		new Worker().go(args);
	}

	private void go(String args[]) throws Exception {
		String letter = args[0];
		System.out.printf("Starting process for letter %s", letter);
		
		File dir = new File("/home/ubuntu/workspace/files");
		
		File[] allFiles = dir.listFiles();
		List<File> files = new ArrayList<File>();
		
		for(File file : allFiles){
		  if(checkFile(file, letter))
		    files.add(file);
		}
		
		String word = args[1];
		
		for(File file : files){
		  try(BufferedReader reader = new BufferedReader(new FileReader(file))){
		    while(reader.ready()){
		      String test = reader.readLine();
		      if(test.contains(word)){
		        System.out.printf("%nFound %s in %s%n", word, file.getName());
		      }
		    }
		    
		  }
		  catch(Exception e){
		    e.printStackTrace();
		  }
		}
		
		  
		
		/*File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return (name.startsWith(letter)) ? true : false;
			}

		});*/
	}
	
	private boolean checkFile(File f, String letter){
	  	return (f.getName().startsWith(letter)) ? true : false;
	}
}
