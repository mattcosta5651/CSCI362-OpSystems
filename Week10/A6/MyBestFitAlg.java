public class MyBestFitAlg extends Swapper{
	@Override
	public boolean load(IProcess p, IMemory mem) throws BlueScreenException{
		Slot best = new Slot(0, Integer.MAX_VALUE, null);
		
		//find best
		for(Slot s : list){
			//if slot is smaller than the current best and big enough for the process
			if(s.getSize() <= best.getSize() && s.getSize() >= p.getSize() && !s.getOccupied())
				best = s;
		}
		
		//inserts into list if slot was found
		if(!(best.getSize() > mem.getSize())){

			int start = best.getStart();
			int end  = start + p.getSize();
				
			insertProcess(best, start, end, p);
			mem.load(p, start, end);
		
			return true;
		}
		
		return false;
	}
}
