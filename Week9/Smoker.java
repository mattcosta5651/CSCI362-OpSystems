import java.util.*;
import java.util.concurrent.*;

public class Smoker extends Dude{
	static String[] ITEMS = {"matches", "papers", "tobacco"};
	
	private int index;
	private int tries = 0;
	private boolean[] items = new boolean[3];
	private String item;
	
	public Smoker(Inventory i, int index){
		super(i);
		this.index = index;
		item = Smoker.ITEMS[index];
		items[index] = true;
	}
	
	public Boolean call() throws Exception{
		while(true){
			try{
				//picks up items on the table
				for(int i = 0; i < items.length; i++){
					if(items[i] == false){
						acquireItem(i);
						System.out.printf("%s Acquired %s%n", item, Smoker.ITEMS[i]);
					}
				}
				
				//has all items; smokes
				if(Arrays.asList(items).contains(false) == false){
					System.out.printf("%s: Puff Puff%n", item);
					Thread.sleep(1000);
					inventory.readyForSupplies();
				}
				else
					releaseItems();
			

				
				 return true;
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	//tries to get items
	public void acquireItem(int index){
		System.out.printf("%s Trying for %s...%n", item, Smoker.ITEMS[index]);
		
		switch (index){
			case 0:
				inventory.consumeMatches();
				items[0] = true;
				break;
			case 1:
				inventory.consumePaper();
				items[1] = true;
				break;
			case 2:
				inventory.consumeTobacco();
				items[2] = true;
				break;
		}
	}
		
	//puts items back on table except self-item
	private void releaseItems(){
		items = new boolean[3];
		items[index] = true;
		
		switch (item){
			case "matches":
				inventory.producePaper();
				inventory.produceTobacco();
				break;
			case "papers":
				inventory.produceMatches();
				inventory.produceTobacco();
				break;
			case "tobacco":
				inventory.produceMatches();
				inventory.producePaper();
				break;
			default:
				break;
		}
		
		System.out.println("Cannot smoke. Releasing items..");
	} 		
}
