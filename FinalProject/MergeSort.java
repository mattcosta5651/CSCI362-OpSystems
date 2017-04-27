import java.util.*;
import java.util.concurrent.*;

public class MergeSort{
	private static int group_id = 0;
	
	public static void main(String[] args) throws Exception{
		new MergeSort().go();
	}
	
	private void go() throws Exception{
		List<Integer> numbers;
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of numbers to generate (power of two): ");
		int nums = Integer.parseInt(scanner.nextLine());

		numbers = generateNumbers(nums);
		
		int chunk = numbers.size();
		int groups = 1;	
		
		for(int i = 1; i < numbers.size(); i*=2){
			if(chunk%i == 0){
				chunk = chunk/2;
				groups = groups*2;
				System.out.printf("Groups: %s of chunk size %s%n", groups, chunk); 
			}
			else
				continue;
		}
			
		List<Callable<List<Integer>>> groupers = new ArrayList<Callable<List<Integer>>>();
		
		int start = 0;
		int range = start+chunk;
		
		for(int i = 0; i < groups; i+=2){
			System.out.printf("From: (%s, %s) to (%s, %s)%n", start, range, range, range+chunk);
			groupers.add(new Grouper(numbers.subList(start, range), numbers.subList(range, range+chunk)));
			start = range+chunk;
			range = start+chunk;
		}

		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Future<List<Integer>>> futures = executor.invokeAll(groupers);

		numbers = new ArrayList<Integer>(); 

		for (Future<List<Integer>> f : futures)
		{
			if (f.isDone()){
				for(Integer i : f.get())
					numbers.add(i);
			}
			else			  
				throw new Exception("Thread completion error!");
		}
		executor.shutdown();
		
		for(Integer i : numbers)
			System.out.println(i);
	}
	
	private List<Integer> generateNumbers(int size){
		List<Integer> numbers = new ArrayList<Integer>(); 
		Random rand = new Random();
		
		for(int i = 0; i < size; i++)
			numbers.add(rand.nextInt(size));
		
		return numbers;
	}
	
	private class Grouper implements Callable<List<Integer>>{
		private int m_id;
		private List<Integer> groupA;
		private List<Integer> groupB;
		private List<Integer> merged;
				
		public Grouper(List<Integer> a, List<Integer> b){
			m_id = group_id++;
			groupA = a;
			groupB = b;
			merged = new ArrayList<Integer>();
		}
		
		public List<Integer> call(){
			//both groups have items
			if(groupA.size() > 0 && groupB.size() > 0){
				System.out.printf("Merging for group %s..%n", m_id);
				merge();
				return this.call();
			}
			//add remainder of existing group, done 
			else{
				if(groupA.size() > 0){
					for(Integer i : groupA)
						merged.add(i);
				}
				else if(groupB.size() > 0){
					for(Integer i : groupB)
						merged.add(i);
				}
				
				return merged;
			}
		}
		
		private void merge(){
			Integer a = groupA.get(0);
			Integer b = groupB.get(0);
			System.out.printf("A: %s B: %s%n", a, b);
			if(a < b){
				merged.add(a);
				groupA.remove(a);
			}
			else if(b < a){
				merged.add(b);
				groupB.remove(b);
			}
			else{
				merged.add(a);
				merged.add(b);
				groupA.remove(a);
				groupB.remove(b);
			}			
		}
	}
}
