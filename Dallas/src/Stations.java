import java.util.*;

public class Stations extends Thread {
			
	Lock lock;
	
	private int delay;
	private String name;
	
	private int conveyorIn;
	private int conveyorOut;
	private int workLoad;

	public Stations(int delay,String name,Lock lock,int conveyorIn,int conveyorOut,int workLoad){
		this.delay = (delay*1000); 
		this.name = name;
		this.lock = lock;
		this.conveyorIn = conveyorIn;
		this.conveyorOut = conveyorOut;
		this.workLoad = workLoad;
	}
	
	public void run() {
		try {
				
			this.setName(this.name);
			Thread.sleep(this.delay);
			System.out.println("Routing "+this.name+": input connection is set to conveyor number "+this.conveyorIn);
			System.out.println("Routing "+this.name+": ouput connection is set to conveyor number "+this.conveyorOut);
			System.out.println("Routing "+this.name+": Workload set. "+this.name+" has a total of "+this.workLoad+" package groups to move");
								
				while(true) {
										
					if(!lock.Relock[conveyorIn].isLocked()) {
						
						lockConveyorIn();
						
						if(!lock.Relock[conveyorOut].isLocked()) {

							lockConveyorOut();
						}
						
						else if(lock.Relock[conveyorOut].isLocked()) {
							System.out.println(this.name+": unable to lock output conveyor "+this.conveyorOut+" - releasing lock on input conveyor "+this.conveyorIn);
							releaseConveyorIn();
						}
						
					}
					
					else if(lock.Relock[conveyorIn].isLocked()) {
						System.out.println(this.name+": unlocks input conveyor "+this.conveyorIn);
					}
					
					Thread.sleep(this.delay);				
			}
			
		} catch (InterruptedException e) {	
			Thread.currentThread().interrupt();
		}
	}
	
	public void  lockConveyorIn() {
		lock.Relock[this.conveyorIn].lock();
		System.out.println(this.name+": holds lock on input conveyor "+this.conveyorIn);
	}
	
	public void lockConveyorOut() {
		System.out.println(this.name+": hold lock on output conveyor "+this.conveyorOut);
		lock.Relock[this.conveyorOut].lock();
		movePackages();		
	}
	
	
	public void releaseConveyorIn() {
		System.out.println(this.name+": unlocks output conveyor "+this.conveyorOut);
		lock.Relock[this.conveyorIn].unlock();
		
	}
	
	public void movePackages() {

		System.out.println(this.name+": successfully moves packages into station on input conveyor "+this.conveyorIn);
		System.out.println(this.name+": successfully moves packages out of station on output conveyor "+this.conveyorOut);
	
		this.workLoad = this.workLoad-1;
		
		System.out.println(this.name+": has "+ this.workLoad+ " package groups left to move");
		
		if(this.workLoad == 0) {
			
			System.out.println("* * "+this.name+": Workload succesfully completed. * *\n\n");
			lock.Relock[this.conveyorIn].unlock();
			lock.Relock[this.conveyorOut].unlock();

			
			Thread.currentThread().interrupt();
		}
		
		else
			releaseAllLocks();
	}
	
	public void releaseAllLocks(){
		
		System.out.println(this.name+": unlocks input conveyor "+this.conveyorIn);
		System.out.println(this.name+": unlocks output conveyor "+this.conveyorOut);
		
		lock.Relock[this.conveyorIn].unlock();
		lock.Relock[this.conveyorOut].unlock();
		
		try {
			Thread.sleep(this.delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	
		
}
