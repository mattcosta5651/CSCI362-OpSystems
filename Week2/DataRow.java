import java.util.*;

public class DataRow{
	
	private ArrayList<DataRow> rows;

	public final static HashMap<String, String> COMMANDS = 
		new HashMap<String, String>(){{
			put("LDA", "0000"); put("ADD", "0001"); 
				put("SUB", "0010"); put("OUT", "1110"); put("HLT", "1111");
	}};
	
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
			put("FH", "1111");
	}};
	
	private String m_row;
	private String m_key;
	private String m_data;
	private boolean full = false;

	//data row containing command
	public DataRow(String row, String[] data, ArrayList<DataRow> r){
		this(row, r);
		m_key = data[0];
		
		//OUT OR HLT
		if(data.length < 1)
			m_data = "XXXX";
		//LDA, ADD, OR SUB
		else{
			//create new data row with binary data
			int difference = 0;
			if(Integer.toBinaryString(Integer.parseInt(data[1])).length() < 8)
				difference = 8 - Integer.toBinaryString(Integer.parseInt(data[1])).length();
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < difference; i++)
				sb.append("0");
			sb.append(Integer.toBinaryString(Integer.parseInt(data[1])));
			
			for(int i = DataRow.MEMORY.size(); i > 0; i--){ 						//moves from bottom up
				if(!rows.get(i).isFull()){ 											//check if row is empty
					if(rows.get(i).getData() != sb.toString()){ 					//data is not already in memory
						m_data = DataRow.MEMORY_CODES.get(DataRow.MEMORY.get(i));	//sets current row data to memory location 
						new DataRow(DataRow.MEMORY.get(i), sb.toString(), rows); 			//constructs new DataRow
					}
				}
			}
			 
		}
	}
	
	//data row containing binary data
	public DataRow(String row, String data, ArrayList<DataRow> r){
		this(row, r);
		m_data = data.substring(0,4)+"\t"+data.substring(5,8);
	}
	
	//default constructor that fills and sets row
	public DataRow(String row, ArrayList<DataRow> r){
		m_row = row;
		full = true;
		rows = r;
	}
	
	public String getKey(){
		return m_key;
	}
	
	public String getData(){
		return m_data;
	}
	
	public boolean isFull(){
		return full;
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(m_key);
		sb.append(m_data);
		return sb.toString();
		
	}
}
