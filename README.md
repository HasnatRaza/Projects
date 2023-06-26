# Project 1: GPX to CSV using Java

A GPX file is a GPS data file saved in the GPS Exchange format, which is an open standard used by many GPS programs. It contains latitude and longitude location data that may include waypoints, routes, and tracks. A CSV file is a comma-separated values file commonly used by spreadsheet programs such as Microsoft Excel. It contains plain text data sets separated by commas, with each new line in the CSV file representing a new database row and each database row consisting of one or more fields separated by a comma.

For this project, you are given the gpx file [triplog.gpx](./triplog.gpx) which was gathered recently over a 3 day trip. Your objective is to read the latitude and longitude data stored within to create a new csv file called triplog.csv so that we can add to the data and have easier access to it in future projects. You are given `Driver.java`, and `triplog.gpx` (do not edit these files). You are required to create and write the class `Convert.java` to complete the project.

## The Data

Inside [triplog.gpx](./triplog.gpx) you will find entries that look like this:

<img src=./resources/gpxtriplog.PNG width=50% height=50%>

Each pair of lat (latitude) and lon (longitude) is a point that was captured during the trip. The points were captured at 5 minute intervals, which is another column we will be adding during the conversion to a csv. We want to grab the data and add a time column starting at 0 (minutes) incrementing by 5 for each further point. The resulting csv should be of the form "Time,Latitude,Longitude" which you can see here in excel: 

<img src=./resources/excelexample.PNG width=50% height=50%>

As well as what it looks like in a txt format: 

<img src=./resources/txtexample.PNG width=50% height=50%>

Don't forget to add the header of "Time,Latitude,Longitude" at the top of your csv.

* Note that the data file used in Zybooks is different from the one in GitHub, so your solution should be a general solution.

## Data Anomalies

There are some inconsistencies in the gpx data you will need to work around. These include extra newlines between lines, extra whitespace on some lines (spaces and tabs), and '?' characters in some of the latitude and longitude values. You should account for these errors while reading the gpx file, and resolve them before writing to the csv file. 

For example, you may see a line that looks like this:

<img src=./resources/anexample.PNG width=50% height=50%> 

Notice the latitude has extra spaces after the 34 and a '?' in the middle of the decimal. These should be removed in your csv as such:

<img src=./resources/anexample2.PNG width=50% height=50%> 

## Required Method

You are only required to implement one method:

`public static void convertFile(String filename)`: Given the name of the gpx file to be converted (triplog.gpx), create a new csv (triplog.csv) which follows the outline above. This method is called in Driver.java which should not be changed.

Other than that, you are free to implement any other helper methods you need in Convert.java to complete the project. 

## Grading

Plagiarism will not be tolerated under any circumstances. Participating students will be penalized depending on the degree of plagiarism. It includes "No-code" sharing among the students. It can lead to academic misconduct reporting to the authority if identical code is found among students. 

You will be graded on: 
* Zybooks submission: 100 points

Note that the data file used in Zybooks is different from the one in GitHub so your solution should be a general solution.

Submit your project before the due date/time. **No late submissions allowed.**
