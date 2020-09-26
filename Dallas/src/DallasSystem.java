import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class DallasSystem {

	
	static Stations station;
	static Lock lock;
	
	static int previous = 0;
	static int time = 0;
	
	private static int randomTimes() {
		
		 int upperBound = 4;
	
		 Random rand = new Random();
		 
		 int randomNumber = rand.nextInt(upperBound);
		
		 return randomNumber;
}
	
		
	
	public static void main(String args[]) {
			lock = new Lock(3);
			lock.start();
		
		//migfht want to add logic for unique time, pursuing on hoping thread logic will take care of this
		for(int i = 0; i < 3; i++) {
			
			time = randomTimes();
			
			if(time == previous || time == 0) {
				
				while(time == previous || time == 0)
					time = randomTimes();
			}
			
			else {
			previous = time;
			}
				
			station = new Stations(time,"Station "+i,lock);
			station.start();
			
		}
		
					
	}
	
}
