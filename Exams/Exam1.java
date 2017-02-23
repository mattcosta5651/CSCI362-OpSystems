import java.io.*;
import java.util.*;

public class Exam1{
	private final int[][] RANGES = { {-20, -10}, {-10, 0}, {0, 10}, {10, 20}, {20, 30}, {30, 40} }; 
	
	public static void main(String[] args) throws Exception{
		Exam1 obj = new Exam1();
		if(args.length == 0)
			obj.runMaster();
		else
			obj.runWorker();
	}
	
	private void runMaster() throws Exception{	
		List<Double> bestX = new ArrayList<Double>();
		List<Double> bestY = new ArrayList<Double>();
		
		List<Process> workers = new ArrayList<Process>();
		for (int[] range : RANGES) {
			Process p = new ProcessBuilder("java", "Exam1", "Worker").start();
			workers.add(p);
			sendParameters(p, range[0], range[1]);
		}

		for (Process worker : workers) {
			worker.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(worker.getInputStream(), "UTF-8"));
			String line = br.readLine();
			while (line != null) {
				bestX.add(Double.parseDouble( line.substring(line.indexOf("(")+1, line.indexOf(","))
						));
				bestY.add(Double.parseDouble( line.substring(line.indexOf(",")+2, line.indexOf(")"))
						));
				
				System.out.println(line);
				line = br.readLine();
			}
		}		
		
		double maxX = Double.MIN_VALUE;
		double maxY = Double.MIN_VALUE;
		for(double y : bestY){
			if(y > maxY)
				maxY = y;
		}
		
		maxX = bestX.get(bestY.indexOf(maxY));
		System.out.printf("Best point is (%.2f, %.2f)", maxX, maxY);
	}
	
	private void runWorker() throws Exception{
		Scanner in = new Scanner(System.in);
		
		double start = Double.parseDouble(in.nextLine());
		double end = Double.parseDouble(in.nextLine());
		
		double max = start; //sets to lowest possible value
		double maxY = runFormula(max);
		for(double i = start; i < end; i+=0.1){
			double y = runFormula(i);
			if(y > maxY){
				max = i;
				maxY = y; 
			}
		}
		System.out.printf("Process found (%.2f, %.2f)...", max, maxY);				
	}
	
	private void sendParameters(Process p, double start, double end) throws Exception {
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));
		pw.println(start);
		pw.println(end);
		pw.flush();		
	}
	
	
	
	private double runFormula(double x){
		return ( ( ( -1 * Math.pow(x,4) ) + ( 20 * Math.pow(x,3) ) ) / 500 ) + 24;
	}
}
