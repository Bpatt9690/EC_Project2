import java.util.concurrent.locks.ReentrantLock;

public class Lock extends Thread {

	static ReentrantLock[] Relock;
	int conveyors;
	
	public Lock(int converyors) {
		this.conveyors = conveyors;
	}
	
	public void run() {
		
		Relock = new ReentrantLock[conveyors];
		
		for(int i = 0; i < 3; i++)
			Relock[i] = new ReentrantLock();
		
	}
	
}
