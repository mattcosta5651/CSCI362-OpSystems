import java.io.*;
import java.util.*;

public class A1{
	private ArrayList<DataRow> rows = new ArrayList<DataRow>(){{
		for(int i = 0; i < DataRow.MEMORY.size(); i++)
			rows.add(new DataRow(DataRow.MEMORY.get(i), rows)); //constructs empty DataRows
		}};
	
	public static void main(String[] args){
		new A1();
	}
	
	public A1(){
		readRows();
		int counter = 0;
		for(String memLoc : DataRow.MEMORY){
			StringBuffer sb = new StringBuffer();
			sb.append(memLoc);
			sb.append(DataRow.MEMORY_CODES.get(memLoc));
			sb.append(rows.get(counter).toString());
			System.out.println(sb.toString());
			counter++;
		}

	}
	
	private void readRows(){
		try(BufferedReader reader = new BufferedReader(new FileReader("program.txt"))){
			int counter = 0;
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(" ");
				
				rows.set(counter, new DataRow(DataRow.MEMORY.get(counter), data, rows));
				counter++;
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}
