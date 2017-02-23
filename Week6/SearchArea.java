import java.util.*;
import java.io.*;

public class SearchArea implements Serializable{
	private Point point;
	private BST<Point> pointsTree;
	
	public SearchArea(Point point, List<Point> pointList){
		this.point = point;
		addPoints(pointList);
	}
	
	public Point getClosestPoint(){
		return (Math.abs(point.getDistance(pointsTree.getLeft().getData())) < Math.abs(point.getDistance(pointsTree.getRight().getData()))) 
			? pointsTree.getLeft().getData() : pointsTree.getRight().getData();
	}
	
	private void addPoints(List<Point> pointList){
		pointsTree = new BST<Point>(point, new BST(point));
		
		for(Point p : pointList)
			pointsTree.add(p);
	}
	
	public BST<Point> getPoints(){return pointsTree;}
}
