import java.util.*;
import java.util.concurrent.*;

public class Inventory{
	private final Semaphore paper = new Semaphore(0, true);
	private final Semaphore matches = new Semaphore(0, true);
	private final Semaphore tobacco = new Semaphore(0, true);
	private final Semaphore supplier = new Semaphore(0, true);
	private final Semaphore smoker = new Semaphore(0, true);
	
	public void producePaper(){
		paper.release();
	}
	
	public void produceMatches(){
		matches.release();
	}
	
	public void produceTobacco(){
		tobacco.release();
	}
	
	public void consumePaper(){
		try{
			paper.acquire();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void consumeMatches(){
		try{
			matches.acquire();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void consumeTobacco(){
		try{
			tobacco.acquire();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public synchronized boolean matchesPermitsAvailable(){return matches.availablePermits() > 0;}
	public synchronized boolean paperPermitsAvailable(){return paper.availablePermits() > 0;}
	public synchronized boolean tobaccoPermitsAvailable(){return tobacco.availablePermits() > 0;}
	
	public void readyForSmoker(){
		
	}
	
	public void readyForSupplies(){
		supplier.release();
	}
	
	
	public void waitForSmoker(){
		try{
			supplier.acquire();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}	
}
