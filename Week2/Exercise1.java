import java.io.*;
import java.util.*;

public class Exercise1{
	private ArrayList<DataRow> rows = new ArrayList<DataRow>(){{for(int i = 0; i < DataRow.MEMORY.size(); i++)
		rows.add(new DataRow(DataRow.MEMORY.get(i), " "));}};
	
	public static void main(String[] args){
		new Exercise1();
	}
	
	public Exercise1(){
		readRows();
		int counter = 0;
		for(String memLoc : DataRow.MEMORY){
			StringBuffer sb = new StringBuffer();
			sb.append(memLoc);
			sb.append(DataRow.MEMORY_CODES.get(memLoc));
			System.out.println(sb.toString());
		}
		/*for(DataRow row : rows){
			StringBuffer sb = new StringBuffer();
			String memLoc = counter+"H\t";
			if(!row.getKey().contains("H"))
				sb.append(memLoc);
			sb.append(DataRow.MEMORY.get(memLoc));
			sb.append(row.toString());
			System.out.println(sb.toString());
		}		*/
	}
	
	private void readRows(){
		rows = new ArrayList<DataRow>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader("program.txt"))){
			while(reader.ready()){
				String line = reader.readLine();
				String[] data = line.split(" ");
				if(checkCommand(data)){
					rows.add(new DataRow(data[0], data[1]));
					
				}
			}
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
	} 
	
	private boolean checkCommand(String[] d){
		return false;
	}
	
	
	
	
	
	private static class DataRow{

		public final static HashMap<String, String> COMMANDS = 
			new HashMap<String, String>(){{
				put("LDA", "0000"); put("ADD", "0001"); 
					put("SUB", "0010"); put("OUT", "1110"); put("HLT", "1111");}};
		
		public final static ArrayList<String> MEMORY = new ArrayList<String>(){{
			add("0H");add("1H");add("2H");add("3H");add("4H");add("5H");add("6H");
			add("7H");add("8H");add("9H");add("AH");add("BH");add("CH");add("DH");
			add("EH");add("FH");
		}};

		public final static HashMap<String, String> MEMORY_CODES = 
			new HashMap<String, String>(){{put("0H", "0000"); put("1H", "0001"); 
				put("3H", "0011");put("4H", "0100");put("5H", "0101");put("6H", "0110");
				put("7H", "0111");put("8H", "1000");put("9H", "1001");put("AH", "1010");
				put("BH", "1011");put("CH", "1100");put("DH", "1101");put("EH", "1110");
				put("FH", "1111");}};
		
		private String m_row;
		private String m_key;
		private String m_data;
		private boolean full = false;

		public DataRow(String row, String key, String data){
			this(row, key);
			m_data = data;
		}
		
		public DataRow(String row, String key){
			m_row = row;
			m_key = key;
			m_data = "XXXX";
			full = true;
		}
		
		public String getKey(){
			return m_key;
		}
		
		public String getData(){
			return m_data;
		}
		
		@Override
		public String toString(){
			StringBuffer sb = new StringBuffer();
			
			if(DataRow.COMMANDS.containsKey(m_key))
				sb.append(DataRow.COMMANDS.get(m_key));
			
			sb.append(m_data);
			return sb.toString();
			
		}
	}
		
}
