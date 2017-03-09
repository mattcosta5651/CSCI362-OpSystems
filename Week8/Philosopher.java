import java.util.concurrent.*;

public class Philosopher extends Party implements Callable<String>{
    private Fork left;
    private Fork right;
    private String name;
    private Waiter waiter;
    
    public Philosopher(String name, Waiter waiter){
        this.name = name;
        this.waiter = waiter;
    }
    
    public String call(){
        requestWaiter();
        return this.toString();
    }
    
    public String getName(){return name;}
    
    public Fork getLeft(){ return left;}
    public Fork getRight(){return right;}
    public synchronized void setLeft(Fork f){left = f;}
    public synchronized void setRight(Fork f){right = f;}
    
    public synchronized void requestWaiter(){ waiter.arrangeForks(); }
    
    public void think(){
        try{
            Thread.sleep(1000);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString(){
        if(state)
            return String.format("%s is eating", name);
        else
            return String.format("%s is thinking", name);
    }
}