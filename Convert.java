import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Convert {
    public static void convertFile(String filename) throws IOException, FileNotFoundException {
        File inputFile = new File(filename);
        File outputFile = new File("triplog.csv");

        try (Scanner scanner = new Scanner(inputFile); FileWriter writer = new FileWriter(outputFile)) {
            // header
            writer.write("Time,Latitude,Longitude\n");

            int time = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("<trkpt")) {
                    // extracting latitude and longitude
                    int latStart = line.indexOf("lat=") + 5;
                    int latEnd = line.indexOf("\"", latStart);
                    String latitude = removeWhiteandChar(line.substring(latStart, latEnd));

                    int lonStart = line.indexOf("lon=") + 5;
                    int lonEnd = line.indexOf("\"", lonStart);
                    String longitude = removeWhiteandChar(line.substring(lonStart, lonEnd));

                    // output latitude, longitude, and time to output file
                    writer.write(String.format("%d,%s,%s\n", time, latitude, longitude));

                    // increment time by 5 minutes
                    time += 5;
                }
            }
        }
    }

    // remove whitespace and ? from string
    private static String removeWhiteandChar(String str) {
        return str.replaceAll("\\s+", "").replace("?", "");
    }
}