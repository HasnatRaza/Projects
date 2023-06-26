import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// read the data
		TripPoint.readFile("triplog.csv");
		
		// find the total distance
		System.out.println("Total distance in km: " + TripPoint.totalDistance());
		
		// find the total time
		System.out.println("Total time: " + TripPoint.totalTime() + " hours");
		
		ArrayList<TripPoint> trip = TripPoint.getTrip();
		
		// find average speed between first two points
		System.out.print("Average speed between first two points: ");
		System.out.println(TripPoint.avgSpeed(trip.get(0), trip.get(1)) + " km/hr");
	}
	

}