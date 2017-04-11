public class MyFirstFitAlg extends Swapper{

	@Override
	public boolean load(IProcess p, IMemory mem) throws BlueScreenException{
		for(Slot s : list){
			if(s.getSize() >= p.getSize() && !s.getOccupied()){
				int start = s.getStart();
				int end = s.getEnd() - p.getSize();
						
				insertProcess(s, start, end, p);
				mem.load(p, start, end);	
								
				return true;
			}
		}
		
		return false;
	}
}
