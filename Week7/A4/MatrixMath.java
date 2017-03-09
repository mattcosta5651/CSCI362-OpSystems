import java.util.*;
import java.util.concurrent.*;

public class MatrixMath{
	public static void main(String[] args) throws Exception{
		new MatrixMath().go();
	}
	
	private void go() throws Exception{
		List<Matrix> matrices = getInput();
		Matrix result = multMatrices(matrices.get(0), matrices.get(1));

		System.out.println(result.getCellTotal());
	}
	
	private Matrix multMatrices(Matrix a, Matrix b){
		Matrix result;
		List<Boolean> workers = new ArrayList<Boolean>();
		
		if(a.getCols() == b.getRows()){
			result = new Matrix(a.getRows(), b.getCols());	
			
			List<Cell> a_row;
			List<Cell> b_col;
			
			//Ay + 1
			for(int y = 0; y < b.getCols(); y++){
				b_col = new ArrayList<Cell>();
				for(int i = 0; i < a.getRows(); i++){
					a_row = new ArrayList<Cell>();
					for(int j = 0; j < b.getRows(); j++){
						a_row.add(a.getCell(i, j));
						b_col.add(b.getCell(j, y));
					}
					
					workers.add(new Worker(a_row, b_col, result).call());
				}
			}
			
			if(!workers.contains(false))
				return result;
		}

		else
			throw new Error("Matrices are incompatible.");		
			
		return null;		
	}
	
	private List<Matrix> getInput() throws Exception{
		List<Matrix> matrices = new ArrayList<Matrix>();
		
		getInput("Enter the file name for Matrix 1: ", matrices);
		getInput("Enter the file name for Matrix 2: ", matrices);
		
		return matrices;
	}
	
	private void getInput(String prompt, List<Matrix> matrices) throws Exception{
		Scanner scanner = new Scanner(System.in);		
		System.out.print(prompt);
		matrices.add(new Matrix(scanner.nextLine()));		
	}
	
	public class Worker implements Callable<Boolean>{
		private List<Cell> a;
		private List<Cell> b;
		private Matrix result;
		
		public Worker(List<Cell> a, List<Cell> b, Matrix result){
			this.a = a;
			this.b = b;
			this.result = result;
		}
		
		@Override
		public Boolean call(){
			int total = 0;
			
			for(int i = 0; i < a.size(); i++)
			
				total += a.get(i).getVal() * b.get(i).getVal();

			result.setCell(a.get(0).getRow(), b.get(0).getCol(), total);
			return true;
		}
	}
}
