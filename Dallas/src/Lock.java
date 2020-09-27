import java.util.concurrent.locks.ReentrantLock;

public class Lock {

	static ReentrantLock[] Relock;
	private int conveyors;

	public Lock(int conveyors) {
		this.conveyors = conveyors;
	}
	
	public void run() {
		
		Relock = new ReentrantLock[this.conveyors];
		
		for(int i = 0; i < conveyors; i++)
			Relock[i] = new ReentrantLock();
		
	}
	
}
