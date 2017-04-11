import java.util.*;

public abstract class Swapper implements ISwapper{
	protected List<Slot> list = new LinkedList<Slot>();
	
	@Override
	public void init(int memSize){
		list.add(new Slot(0, memSize, null));
	}
	
	@Override
	abstract public boolean load(IProcess p, IMemory mem) throws BlueScreenException;
	
	@Override
	public void unload(IProcess p, IMemory mem) throws BlueScreenException{
		//removes from memory
		mem.unload(p);
		
		for(int i = 0; i < list.size(); i++){
			Slot next = null;
			
			//sets the next, if not end of list
			if(i+1 < list.size())
				next = list.get(i + 1);
			
			//removes from list
			if(list.get(i).getOccupied() && list.get(i).getProcess().getId() == p.getId()){
				Slot s = list.get(i);
				list.add(i, new Slot(s.getStart(), s.getEnd(), null));
				list.remove(s);	
			}
		
			//joins holes
			if(next != null){
				if(!list.get(i).getOccupied() && !next.getOccupied()){
					Slot s = list.get(i);
					list.add(i, new Slot(s.getStart(), next.getEnd(), null));
					list.remove(s);
					list.remove(next);
				}
			}
		}
	}
	
	protected void insertProcess(Slot container, int start, int end, IProcess p){
		int index = list.indexOf(container);
		
		//adds slot containing process
		list.add(index, new Slot(start, end, p));
		
		//adds remainder hole to new slot
		list.add(index+1, new Slot(end+1, container.getEnd(), null));
		
		//removes slot from list
		list.remove(container);		
	} 
	
	protected class Slot{
		private int start;
		private int end;
		private boolean occupied;
		private IProcess process;
		
		public Slot(int start, int end, IProcess process){
			this.start = start;
			this.end = end;
			this.process = process;
			
			if(process == null)
				this.occupied = false;
			else
				this.occupied = true;
		}
		
		public int getStart(){return start;}
		public int getEnd(){return end;}
		public int getSize(){return end-start;}
		public boolean getOccupied(){return occupied;}
		public IProcess getProcess(){return process;}
	}
	
}
