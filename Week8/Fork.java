public class Fork extends Party
{
    private Philosopher left;
    private Philosopher right;
    
    public Fork(Philosopher left, Philosopher right){
        this.left = left;
        this.right = right;
        
        left.setRight(this);
        right.setLeft(this);
    }
}