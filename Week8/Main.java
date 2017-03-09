import java.util.*;

public class Main{
    public static void main(String[] args){
        new Main().go();
    }
    
    private void go(){
        Waiter waiter = new Waiter();
        List<Fork> forks = waiter.getForks();
        List<Philosopher> phillies = waiter.getPhillies();
        
        boolean party = true;
        
        while(party){
            for(Philosopher p : phillies){
                p.call();
            }
        }
    }
}