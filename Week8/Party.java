public abstract class Party{
    protected boolean state = false;
    
    public boolean getState(){
        return state;
    }
    
    public void setState(boolean state){
        this.state = state;
    }
}