import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

public class TripPoint {
	private double lon;	
	private int time;
	private double lat;	
	
	private static ArrayList<TripPoint> trip;
	private static ArrayList<TripPoint> movingTrip;
	
	public TripPoint() {
		lat = 0.0;
		lon = 0.0;
		time = 0;
	}
	public TripPoint(int time, double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
		this.time = time;
	}
	public double getLon() {
		return lon;
	}
	
	public int getTime() {
		return time;
	}
	public double getLat() {
		return lat;
	}
	

	public static ArrayList<TripPoint> getTrip() {
		return new ArrayList<>(trip);
	}

	public static double haversineDistance(TripPoint first, TripPoint second) {
		double lat1 = first.getLat();
		double lat2 = second.getLat();
		double lon1 = first.getLon();
		double lon2 = second.getLon();
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                   Math.pow(Math.sin(dLon / 2), 2) *
                   Math.cos(lat1) *
                   Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
	}
	
	public static void readFile(String filename) throws FileNotFoundException, IOException {
		File file = new File(filename);
		Scanner fileScanner = new Scanner(file);
		trip = new ArrayList<TripPoint>();
		String[] fileData = null;
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			fileData = line.split(",");
			if (!line.contains("Time")) {
				trip.add(new TripPoint(Integer.parseInt(fileData[0]), Double.parseDouble(fileData[1]), Double.parseDouble(fileData[2])));
			}
		}
		fileScanner.close();
	}
	
	
	public static double avgSpeed(TripPoint a, TripPoint b) {
		int timeInMin = Math.abs(a.getTime() - b.getTime());
		double dis = haversineDistance(a, b);
		double kmpmin = dis / timeInMin;		
		return kmpmin*60;
	}
	
	public static double totalTime() {
		int minutes = trip.get(trip.size()-1).getTime();
		double hours = minutes / 60.0;
		return hours;
	}
	
	public static double totalDistance() throws FileNotFoundException, IOException {
		double distance = 0.0;
		if (trip.isEmpty()) {
			readFile("triplog.csv");
		}
		for (int i = 1; i < trip.size(); ++i) {
			distance += haversineDistance(trip.get(i-1), trip.get(i));
		}
		return distance;
	}

	
	public static int h1StopDetection() {
		movingTrip = new ArrayList<TripPoint>(trip);
		ArrayList<Integer> stopPoints = new ArrayList<>();
		int stop = 0;
		for(int i = 0; i < trip.size() - 1; i++) {	
			if(haversineDistance(trip.get(i), trip.get(i + 1)) <= 0.6) {
				stop++;
				stopPoints.add(trip.get(i + 1).getTime());
			}
		}
		while(stopPoints.size() > 0) {
			int target = stopPoints.get(0);
			stopPoints.remove(0);
			for(int i = 0; i < movingTrip.size(); i++) {		
				if(movingTrip.get(i).getTime() == target) {
					movingTrip.remove(i);
				}
			}
		}
		return stop;
	}

	public static int h2StopDetection() {
		ArrayList<TripPoint> temp = new ArrayList<TripPoint>();
		HashSet<Integer> stops = new HashSet<Integer>();
		for(int i = 0; i < trip.size() - 1; i++){
			if(temp.isEmpty()) {
				temp.add(trip.get(i));
				continue;
			}
			boolean pointAdded = false;
			for(int j = 0; j < temp.size(); j++){
				if(haversineDistance(trip.get(i), temp.get(j)) <= 0.5){
					temp.add(trip.get(i));
					pointAdded = true;
					break;
				}
			}
			if(temp.size() > 2){
				for(int j = 0; j < temp.size(); j++){		
					stops.add(temp.get(j).getTime());
				}
			}
			if(!pointAdded) {
				temp = new ArrayList<TripPoint>();
				temp.add(trip.get(i));
			}	
		}
		ArrayList<Integer> stopsList = new ArrayList<Integer>(stops);
		int numStops = stopsList.size();
		movingTrip = new ArrayList<TripPoint>(trip);
		while(stopsList.size() > 0) {
			int target = stopsList.get(0);
			stopsList.remove(0);
			for(int i = 0; i < movingTrip.size(); i++) {
				if(movingTrip.get(i).getTime() == target) {
					movingTrip.remove(i);
				}
			}
		}
		return numStops;
	}

	public static double movingTime() {
		return totalTime()- stoppedTime();
	}

	public static ArrayList<TripPoint> getMovingTrip(){	
		return new ArrayList<>(movingTrip);
		}

	public static double stoppedTime(){
		int stoppedPoints = trip.size() - movingTrip.size();
		int mins = stoppedPoints * 5;
		double hrs = mins / 60.0;
		
		return hrs;
	}

	public static double avgMovingSpeed(){
		double totalDistance = 0;
		for(int i = 0; i < movingTrip.size() - 1; i++){
			totalDistance += haversineDistance(movingTrip.get(i), movingTrip.get(i + 1));
		}
		double avgMovingSpeed = totalDistance / movingTime();
		return avgMovingSpeed;
		}

	}


