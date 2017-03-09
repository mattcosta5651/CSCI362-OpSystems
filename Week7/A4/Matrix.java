import java.io.*;
import java.util.*;

public class Matrix{
	ArrayList<ArrayList<Cell>> matrix;
	
	public Matrix(String filename) throws Exception{
		matrix = new ArrayList<ArrayList<Cell>>();
		int row = 0;
		int col = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		String line = reader.readLine();
		while(line != null){				
			matrix.add(new ArrayList<Cell>());
			String[] chars = line.split(",");
			col = 0;
			for(String c : chars){		
				c = c.replaceAll("\\s", "");
				try{
					setCell(row, col, Integer.parseInt(c));
				}
				catch(Exception e){
					throw new Error("Matrix must contain (only) numbers");
				}
				col++;
			}
			
			line = reader.readLine();
			row++;
		}
	}
	
	public Matrix(int x, int y){
		matrix = new ArrayList<ArrayList<Cell>>();
		
		if(x <= 0 || y <= 0)
			throw new Error("Dimensions must be positive integers");
		
		for(int i = 0; i < x; i++)
			matrix.add(new ArrayList<Cell>());
		
		
		for(int i = 0; i < x; i++){
			for(int j = 0; j < y; j++)
				matrix.get(i).add(new Cell(i, j, 0));
		}
	}
	
	public int getCellTotal(){
		int total = 0;
		
		for(List<Cell> i : matrix){
			for(Cell cell : i)
				total+=cell.getVal();
		}
		
		return total;		
	}
	
	public int getRows(){return matrix.size();}
	public int getCols(){return matrix.get(0).size();}
	public Cell getCell(int row, int col){return matrix.get(row).get(col);}
	public synchronized void setCell(int row, int col, int value){
		if(matrix.get(row).size() <= col)
			matrix.get(row).add(new Cell(row, col, 0));
		matrix.get(row).set(col, new Cell(row, col, value));
	}
	
	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		for(List<Cell> i : matrix){
			for(Cell cell : i)
				sb.append(cell+"\t");
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
