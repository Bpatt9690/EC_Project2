import java.util.*;

public class Stations extends Thread {
			
	Lock lock;
	
	private int delay;
	private String name;
	
	private int conveyorIn;
	private int conveyorOut;
	private int workLoad;

	public Stations(int delay,String name,Lock lock,int conveyorIn,int conveyorOut,int workLoad){
		this.delay = (delay*1000); //Converting miliseconds to seconds
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
				//System.out.println(this.name+" is online with time "+this.delay/1000);
				System.out.println(this.name+" Conveyor in is: "+conveyorIn+" Conveyor out is: "+conveyorOut+" Workload is "+this.workLoad+"\n");
				
				
				//add logic here
				while(true) {
				
				if(!lock.Relock[1].isLocked()) {
					System.out.println(this.name+" Lock 1 is unlocked");
					lock.Relock[1].lock();
				}
				Thread.sleep(this.delay);
				System.out.println(this.name+" lock 1 locked\n");	
			}
			
			
		} catch (InterruptedException e) {
				
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
