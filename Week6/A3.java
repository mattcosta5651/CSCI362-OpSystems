import java.util.*;
import java.io.*;

public class A3{ 
	private Point point;
	private final int NUM_POINTS = 100; 
	private final int AREA_GROUPS = NUM_POINTS/10;
	
	public static void main(String[] args) throws Exception{
		A3 obj = new A3();
		
		if(args.length == 0)
			obj.runMaster();
		else
			obj.runWorker();
	}
	
	private void runMaster() throws Exception{
		List<Process> workers = new ArrayList<Process>();
		
		List< Point> points = makePoints();
		
		for(int i = 0; i < points.size(); i+= AREA_GROUPS){
			Process p = new ProcessBuilder("java", "A3", "Worker").start();
			workers.add(p);

			sendParameters(p, new SearchArea(point, points.subList(i, i+AREA_GROUPS)));	
		}
		
		List<Point> closestPoints = new ArrayList<Point>();
		for(Process p : workers){
			p.waitFor();
			closestPoints.add(workerOutput(p, new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"))));
		}
	
		Process finalWorker = new ProcessBuilder("java", "A3", "Worker").start();
		sendParameters(finalWorker, new SearchArea(point, closestPoints));
		finalWorker.waitFor();
		System.out.println("CLOSEST "+workerOutput(finalWorker, new BufferedReader(new InputStreamReader(finalWorker.getInputStream(), "UTF-8"))));
	}
	
	//worker 
	private void runWorker() throws Exception{
		System.out.println(new Scanner(System.in).nextLine());
	}
	
	private void sendParameters(Process p, SearchArea a) throws Exception {
		System.out.println("Starting process...");
		PrintWriter out = new PrintWriter(new OutputStreamWriter(p.getOutputStream(), "UTF-8"));

		out.println(a.getClosestPoint());
		out.flush();
	}
	
	private Point workerOutput(Process p, BufferedReader in) throws Exception{
		String line = in.readLine();
		
		Point retPoint = new Point(Integer.parseInt(line.substring(line.indexOf(":")+2, line.indexOf(","))), Integer.parseInt(line.substring(line.indexOf(",")+2)));
		return retPoint;
	}	
	
	//asks user for a point, randomly generates other points
	private List<Point> makePoints(){
		List<Point> points = new ArrayList<Point>();
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter an x coordinate: ");
		int x = scanner.nextInt();
		System.out.print("Enter an y coordinate: ");
		int y = scanner.nextInt();		
		
		point = new Point(x, y);
		points = new ArrayList<Point>();
		
		for(int i = 0; i < NUM_POINTS; i++)
			points.add(makePoint(points));
			
		return points;
	}
	
	//randomly creates a point
	private Point makePoint(List<Point> points){
		Point p = new Point(new Random().nextInt(20), new Random().nextInt(20));	
		if(!(p == point) || !(points.contains(p))) //prevents duplicate points
			return p;
		else
			return makePoint(points);
	}
}
