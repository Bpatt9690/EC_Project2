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
	
	private int stationCount;
	
	private static int randomTimes() {
		
		 int upperBound = 4;
	
		 Random rand = new Random();
		 
		 int randomNumber = rand.nextInt(upperBound);
		
		 return randomNumber;
	}
	
	
	
	private static void ReadInput() {
		
		
		File inputFile = new File("src/input.txt");
		Scanner myReader;
		List<String> list = new ArrayList<>();
		
		
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
		
		System.out.println(list);
		
	
	}
	
	
	
	
	
		
	public static void main(String args[]) {

		int stationCount = 4;
		int conveyorIn; 
		int conveyorOut;
			
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
			
			String stationName = "Station " +i;
			
			
			if(i == 0) {
				conveyorIn = i; 
				conveyorOut = stationCount -1;
			}
			
			else {
				conveyorIn = i-1;
				conveyorOut = i;
				
				
			}
				
			station = new Stations(time,stationName,lock,conveyorIn,conveyorOut);
			station.start();
			
		}				
	}
}
