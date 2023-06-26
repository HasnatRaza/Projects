

import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		// convert triplog.gpx into triplog.csv
		Convert.convertFile("triplog.gpx");
		
	}
	

}
