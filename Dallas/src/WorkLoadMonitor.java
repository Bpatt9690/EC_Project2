
public class WorkLoadMonitor {
	
	private int workLoad;

	private int completed;
	
	public WorkLoadMonitor(int workLoad) {
		this.workLoad = workLoad;
	}
	
	public void completedWorkLoad() {
		
		this.completed+=1;
		
		if(this.completed == this.workLoad) {
			System.out.println("****ALL WORKLOADS COMPLETE***SIMULATION ENDS****");
		}
		
	}
			
}
