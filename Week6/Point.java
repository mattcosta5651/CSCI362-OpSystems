import java.io.*;

public class Point implements Serializable, Comparable<Point>{
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public String getPoint(){return String.format("x: %s,y: %s", x, y);}
	public int getXDistance(Point other){
		return getX()-other.getX();
	}
	public int getYDistance(Point other){
		return getY()-other.getY();
	}
	public double getDistance(Point other){
		double distance = (Math.sqrt(
							Math.pow(getXDistance(other), 2) + 
								Math.pow(getYDistance(other), 2)
							)
		);
		
		if(getXDistance(other) < 0 || getYDistance(other) < 0)
			distance *= -1;
		
		return distance;
	}
	
	public int compareTo(Point other){
		if(getDistance(other) < 0)
			return 0;
		else
			return 1;
	}
	
	@Override
	public String toString(){
		return String.format("POINT: %s, %s", getX(), getY());
	}
} 
