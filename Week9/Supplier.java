import java.util.*;
import java.util.concurrent.*;

public class Supplier extends Dude{
	public Supplier(Inventory i){
		super(i);
		
	}
	
	public Boolean call() throws Exception{
		while(true){		
			//getting ready
			System.out.println("Getting ready....");
			Thread.sleep(new Random().nextInt(500) + 500);
		
			produceItems();
			inventory.waitForSmoker();

			return true;
		}
	}
	
	private void produceItems(){
		double i = Math.random() * 3;
		
		//not paper
		if(i < 1){
			System.out.println("Mathces and tobacco");
			inventory.produceMatches();
			inventory.produceTobacco();
		}
			
		//not matches
		else if(i > 1 && i < 3){
			System.out.println("Papers and tobacco");
			inventory.producePaper();
			inventory.produceTobacco();
		}		
		//not tobacco
		else{
			System.out.println("Mathces and papers");
			inventory.produceMatches();
			inventory.producePaper();
		}
		
		System.out.println("Putting items on table...");
	}
}
