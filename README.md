# Project 2: Aggregate Data

In Project 2, we will continue working with our triplog dataset. This dataset contains latitude and longitude data that was gathered over a 3 day trip. Now that we have our data converted to a CSV, we want to begin extracting information from it and transform it into useful metrics to analyze the data. You will be given the file [triplog.csv](./triplog.csv), which is the same as the file you created last project. We are interested in using the data stored within to create useful functions that we will use in future projects. For example, finding the total distance traveled during the trip or the average speed. In this project, you will create the class `TripPoint.java` in order to implement a number of methods for this purpose. 

## TripPoint UML

<img src=./resources/UML.PNG width=50% height=50%>

Feel free to add your own helper methods as needed. 

## Methods

### Constructors

`TripPoint(int time, double lat, double lon)`: Initialize the class fields `time`, `lat`, and `lon`.

### Getters

`getTime()`: Should return `time`.

`getLat()`: Should return `lat`.

`getLon()`: Should return `lon`.

`getTrip()`: Should return a copy of the `trip` ArrayList

### Other Methods

`readFile(String filename)`: Read in the data from [triplog.csv](./triplog.csv) to the `trip` ArrayList. The idea is to initialize each line of data (Time,Latitude,Longitude) as a TripPoint object. You can then fill the `trip` ArrayList with those TripPoint objects. 

`totalTime()`: Should return the total time of the trip in hours. Remember the Time column in the data is in minutes. 

### Calculating Distance 

For the remaining methods, we need a way to calculate the distance given two points of latitude and longitude. For this calcuation, we will be using the [Haversine formula](https://en.wikipedia.org/wiki/Haversine_formula) which is a commonly used formula for calculating the distance between two points on a sphere, such as the Earth. There are some pros and cons to using this to analyze our GPS data. 

The Pros:
1. Haversine distance is relatively easy to implement and is widely available in programming languages and libraries.
2. Haversine distance is a fast and efficient calculation that can handle large datasets.

The Cons: 
1. Haversine distance assumes that the Earth is a perfect sphere, which is not entirely accurate as the Earth is slightly oblate (ellipsoidal).
2. Haversine distance does not take into account changes in altitude, which can affect the actual distance traveled between two points.

So our calculation won't be perfect, but for our purposes it will be close enough. 

`haversineDistance(TripPoint a, TripPoint b)`: Should compute and return the Haversine distance between two points in kilometers. 

`totalDistance()`: Should compute and return the total distance of the trip in kilometers. Meaning the total distance between every point in the `trip` ArrayList. 

`avgSpeed(TripPoint a, TripPoint b)`: Should return the average speed between two points in kilometers per hour. This method should work no matter which order the points are given in. 

## Grading

Plagiarism will not be tolerated under any circumstances. Participating students will be penalized depending on the degree of plagiarism. It includes "No-code" sharing among the students. It can lead to academic misconduct reporting to the authority if identical code is found among students. 

You will be graded on: 
* Zybooks submission: 80 points
* Github commits (10 commits): 10 points
* Javadocs: 10 points

Note that the data file used in Zybooks is different from the one in GitHub so your solution should be a general solution.

Submit your project before the due date/time. **No late submissions allowed.**
