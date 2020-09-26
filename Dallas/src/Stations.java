import java.util.*;

public class Stations extends Thread {
		
	private int delay;
	private String name;
	Lock lock;

	public Stations(int delay,String name,Lock lock){
		this.delay = (delay*1000); 
		this.name = name;
		this.lock = lock;
	}
	
	public void run() {
		try {
			
			
			this.setName(this.name);
			
			while(true) {
				Thread.sleep(this.delay);
				System.out.println("Station "+this.name+" is online with time "+this.delay/1000);

				
				if(!lock.Relock[1].isLocked()) {
					System.out.println("Lock 1 is unlocked");
					lock.Relock[1].lock();
				}
				
				System.out.println("lock 1 is locked\n");
							
			}
			
		} catch (InterruptedException e) {
				
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	

}
