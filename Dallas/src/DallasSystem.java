import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.Scanner;

public class DallasSystem {
	
	static Stations station;
	static Lock lock;
	
	static int previous = 0;
	static int time = 0;
	static List<String> list;
	
	static int stationCount;
	
	private static int randomTimes() {
		
		 int upperBound = 4;
	
		 Random rand = new Random();
		 
		 int randomNumber = rand.nextInt(upperBound);
		
		 return randomNumber;
	}
	
	
	private static void ReadInput() {
		
		File inputFile = new File("src/input.txt");
		Scanner myReader;
		list = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader("src/input.txt"))) {
		    list = br.lines().collect(Collectors.toList());    
		}
	
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stationCount = Integer.parseInt(list.get(0));
		
	}

	public static void main(String args[]) {

		int conveyorIn; 
		int conveyorOut;
		String stationName;
		int workload;
			
		ReadInput();
				
		lock = new Lock(stationCount);
		lock.run();
	
		for(int i = 0; i < stationCount; i++) {
			
			time = randomTimes();
			
			if(time == previous || time == 0) {				
				while(time == previous || time == 0)
					time = randomTimes();
			}
			
			else {
				previous = time;
			}
			
			stationName = "Station " +i;
			workload = Integer.parseInt((list.get(i+1)));
			
			if(i == 0) {
				conveyorIn = i; 
				conveyorOut = stationCount -1;
			}
			
			else {
				conveyorIn = i-1;
				conveyorOut = i;
			}
				
			station = new Stations(time,stationName,lock,conveyorIn,conveyorOut,workload);
			station.start();
			
		}				
	}
}
