

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class TestConvert {

	@Test
	void testRowCount() throws IOException, FileNotFoundException {
		Convert.convertFile("triplog.gpx");
		
		File actFile = new File("triplog.csv");
		
		Scanner actScanner = new Scanner(actFile);
		
		int i = 0;
		while(actScanner.hasNextLine()) {
			String actual = actScanner.nextLine();
			++i;
		}
		
		assertEquals(790, i);
		
		actScanner.close();
	}
	
	@Test
	void testFirstValue() throws IOException, FileNotFoundException {
		Convert.convertFile("triplog.gpx");
		
		File actFile = new File("triplog.csv");
		
		Scanner actScanner = new Scanner(actFile);
		
		String line = "";
		for (int i = 0; i < 2; ++i) {
			line = actScanner.nextLine();
		}
		
		assertEquals("0,35.211037,-97.438866", line);
		
		actScanner.close();
	}
	
	@Test
	void testLastValue() throws IOException, FileNotFoundException {
		Convert.convertFile("triplog.gpx");
		
		File actFile = new File("triplog.csv");
		
		Scanner actScanner = new Scanner(actFile);
		
		String line = "";
		for (int i = 0; i < 790; ++i) {
			line = actScanner.nextLine();
		}
		
		assertEquals("3940,35.260788,-97.47337", line);
		
		actScanner.close();
	}
	
	@Test
	void testRandomValues() throws IOException, FileNotFoundException {
		Convert.convertFile("triplog.gpx");
		
		File actFile = new File("triplog.csv");
		
		Scanner actScanner = new Scanner(actFile);
		
		String line1 = "";
		String line2 = "";
		String line3 = "";
		for (int i = 0; i < 790; ++i) {
			String line = actScanner.nextLine();
			if (i == 100) {
				line1 = line;
			}
			if (i == 200) {
				line2 = line;
			}
			if (i == 300) {
				line3 = line;
			}
		}
		
		assertEquals("495,35.10553,-106.68067", line1);
		assertEquals("995,35.17436,-113.7846", line2);
		assertEquals("1495,36.230103,-116.7672", line3);

		actScanner.close();
	}

}
