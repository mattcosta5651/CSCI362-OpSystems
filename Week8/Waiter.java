import java.util.*;

public class Waiter{
    private List<Philosopher> phillies;
    private List<Fork> forks;
    
    public Waiter(){
        phillies = new ArrayList<Philosopher>();
        phillies.add(new Philosopher("dude0", this));
        phillies.add(new Philosopher("dude1", this));
        phillies.add(new Philosopher("dude2", this));
        phillies.add(new Philosopher("dude3", this));
        phillies.add(new Philosopher("dude4", this));     
        
        forks = Collections.synchronizedList(new ArrayList<Fork>());
        
        for(int i = 0; i < phillies.size(); i++){
            if(i == phillies.size() -1)
                forks.add(new Fork(phillies.get(0), phillies.get(i)));
            else
                forks.add(new Fork( phillies.get(i+1), phillies.get(i)));
        }
    }
    
    public synchronized void arrangeForks(){
        for(Philosopher p : phillies){
            //has forks
            if(p.getState()){
                System.out.println(p.getName() + " has both forks");
                System.out.println(p.getName() + " is putting down both forks");
                p.getLeft().setState(false);
                p.getRight().setState(false);
                p.setState(false);
            }
            //want to eat
            else if(!p.getLeft().getState() && !p.getRight().getState()){
                System.out.println(p.getName() + " is picking up both forks");
                p.getLeft().setState(true);
                p.getRight().setState(true);
                p.setState(true);
                System.out.println(p.getName()+ " is eating.");
            }
            else{
                p.think();
                System.out.println(p.getName() + " is thinking");
            }
        }
    }
    
    public List<Fork> getForks(){
        return forks;
    }
    
    public List<Philosopher> getPhillies(){
        return phillies;
    }
}