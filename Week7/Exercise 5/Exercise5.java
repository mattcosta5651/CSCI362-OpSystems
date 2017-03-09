import java.util.*;
import java.util.concurrent.*;

public class Exercise5{
	public static void main(String[] args) throws Exception{
		new Exercise5().go();
	}
	private void go() throws Exception
	{
		List<Double> allInput = Collections.synchronizedList(new ArrayList<Double>());

		List<Callable<Boolean>> tasks = new ArrayList<Callable<Boolean>>();
		for (double n = -5; n < 5; n++)
		{
		  double start = (n*100);
		  double end = start + 100;
		  System.out.printf("Creating thread for range %.0f-%.0f ...%n",start,end);

		  Callable<Boolean> task = new MyThread(start, end, allInput);
		  tasks.add(task);
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Boolean>> futures = executor.invokeAll(tasks);

		for (Future<Boolean> f : futures)
		{
			if (!f.get())
			  throw new Exception("Thread completion error!");
		}
		executor.shutdown();

		for (double i : allInput)
		{
			System.out.printf("%.1f = %.1f %n", i, f(i));
		}
	}
	
	private double f(double x){
		return Math.pow(x, 5) + 3.5*Math.pow(x, 4) - 2.5*Math.pow(x, 3) - 12.5*Math.pow(x, 2) + 1.5*x + 9;
	}
	
	public class MyThread implements Callable<Boolean>
	{
		private double m_start;
		private double m_end;
		private List<Double> m_allInput;
		
		public MyThread(double start, double end, List<Double> allInput)
		{
			m_start = start;
			m_end = end;
			m_allInput = allInput;
		}
		
		@Override
		public 	Boolean call()
		{
		      for (double i = m_start; i < m_end; i+=0.1)
		      {

				double output = f(i);
				
				if(output > -2 && output < 2)
					m_allInput.add(i);
			
		  }
			  return true;
		}	
	}	
}
