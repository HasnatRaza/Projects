import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TripPoint {
    private int time;
    private double lat;
    private double lon;
    private static ArrayList <TripPoint> trip;

    public TripPoint(int time, double lat, double lon) {
        this.time = time;
        this.lat = lat;
        this.lon = lon;
    }

    public int getTime() {
        return time;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
    public static ArrayList<TripPoint> getTrip() {
		return trip;
	}
    
    public static double haversineDistance(TripPoint a, TripPoint b) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(b.getLat() - a.getLat());
        double lonDistance = Math.toRadians(b.getLon() - a.getLon());
        double a1 = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a1), Math.sqrt(1 - a1));

        return R * c;
    }
    
    public static double totalDistance() {
		double fullDistance = 0.0;
		
		for(int i = 0; i < trip.size() - 1; i++) {
			
			TripPoint a = trip.get(i);
			TripPoint b = trip.get(i + 1);
			fullDistance += haversineDistance(a, b);
		}
		
		return fullDistance;
	}
    public static double avgSpeed(TripPoint a, TripPoint b) {
		
		double distance = haversineDistance(a, b);
		double speedTime = Math.abs(a.getTime() - b.getTime()) / 60.0;

	return distance / speedTime; 
}
    
    public static double totalTime() {
		double time = 0;
		
		for(int i = 0; i < trip.size() - 1; i++) {
			TripPoint a = trip.get(i);
			TripPoint b = trip.get(i + 1);
			time += Math.abs(b.getTime() - a.getTime()) / 60.0;
		}
		
		return time; 
	}
public static void readFile(String filename) {
		
		trip = new ArrayList<TripPoint>();
		
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(new File(filename)));
			String row;
			
			
			fileReader.readLine(); 
			while ((row = fileReader.readLine()) != null) {
				String[] data = row.split(",");
				int time = Integer.parseInt(data[0]);
				double lat = Double.parseDouble(data[1]);
				double lon = Double.parseDouble(data[2]);
				
				TripPoint allData = new TripPoint(time, lat, lon);
				trip.add(allData);
			}
			
			fileReader.close();
			
		}
		catch (IOException bruh) {
			bruh.printStackTrace();
		}
	}
}
