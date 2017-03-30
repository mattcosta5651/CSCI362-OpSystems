import java.util.*;
import java.util.concurrent.*;

public abstract class Dude implements Callable<Boolean>{
	protected final Inventory inventory;
	
	public Dude(Inventory i){
		inventory = i;
	}
	
	public abstract Boolean call() throws Exception;
}
