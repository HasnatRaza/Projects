import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TripPointTest {

	@Test
	void testConstructorsAndGetters() {
		TripPoint a = new TripPoint(0, 36.5, 28.2);
		TripPoint b = new TripPoint(5, 42.3, -87.9);
		
		assertEquals(0, a.getTime());
		assertEquals(42.3, b.getLat());
		assertEquals(-87.9, b.getLon());
	}
	
	@Test
	void testGetTrip() throws FileNotFoundException, IOException {
		TripPoint.readFile("triplog.csv");
		ArrayList<TripPoint> trip = TripPoint.getTrip();
		assertEquals(789, trip.size());
		assertEquals(3940, trip.get(trip.size()-1).getTime());
	}
	
	@Test
	void testHaversineDistance() {
		TripPoint a = new TripPoint(0, 36.5, 28.2);
		TripPoint b = new TripPoint(140, 42.3, -87.9);
		
		BigDecimal actual = new BigDecimal(Double.toString(TripPoint.haversineDistance(a, b)));
		actual = actual.setScale(1, RoundingMode.HALF_UP);
		
		assertEquals(9120.7, actual.doubleValue());
	}
	
	@Test
	void testTotalDistance() throws FileNotFoundException, IOException {
		TripPoint.readFile("triplog.csv");
		
		BigDecimal actual = new BigDecimal(Double.toString(TripPoint.totalDistance()));
		actual = actual.setScale(3, RoundingMode.HALF_UP);
		
		assertEquals(4861.235, actual.doubleValue());
	}
	
	@Test
	void testTotalTime() throws FileNotFoundException, IOException {
		TripPoint.readFile("triplog.csv");
		
		BigDecimal actual = new BigDecimal(Double.toString(TripPoint.totalTime()));
		actual = actual.setScale(2, RoundingMode.HALF_UP);
		
		assertEquals(65.67, actual.doubleValue());
	}
	
	@Test
	void testAvgSpeed() {
		TripPoint a = new TripPoint(140, 36.5, 28.2);
		TripPoint b = new TripPoint(5, 42.3, -87.9);
		
		BigDecimal actual = new BigDecimal(Double.toString(TripPoint.avgSpeed(a, b)));
		actual = actual.setScale(2, RoundingMode.HALF_UP);
		
		assertEquals(4053.64, actual.doubleValue());
		
		actual = new BigDecimal(Double.toString(TripPoint.avgSpeed(b, a)));
		actual = actual.setScale(2, RoundingMode.HALF_UP);
		
		assertEquals(4053.64, actual.doubleValue());
	}

}
