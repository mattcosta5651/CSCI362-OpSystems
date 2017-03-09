public class Cell{
	private int row;
	private int col;
	private int value;
	
	public Cell(int x, int y, int val){
		row = x;
		col = y;
		value = val;
	}
	
	public int getRow(){return row;}
	public int getCol(){return col;}
	public int getVal(){return value;}
	
	@Override
	public String toString(){
		return value+"";
	}
}
