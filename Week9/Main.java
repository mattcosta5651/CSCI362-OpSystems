import java.util.*;
import java.util.concurrent.*;

public class Main{
	
	public static void main(String[] args) throws Exception{
		new Main().go();
	}
	
	private void go() throws Exception{
		Inventory inventory = new Inventory();
		
		List<Callable<Boolean>> threads = new ArrayList<Callable<Boolean>>();
		
		threads.add(new Smoker(inventory, 0));
		threads.add(new Smoker(inventory, 1));
		threads.add(new Smoker(inventory, 2));
		threads.add(new Supplier(inventory));
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.invokeAll(threads);
		executor.shutdown();
	}
}
