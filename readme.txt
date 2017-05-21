                                                    README
		                                           	======

	                                     Assignment 4.02 release
                                         ------------------------



Author : Clayton
Date :21 May 2017
Version : 4.02

Hey there congratulations you made it to my assignment 4 README file. This README file contains
a brief explanation of awesome features that have been implemented in this assignment.



Requirements
------------
JDK 1.8



CONTENTS
--------

This sample zip contains:

    /readme.txt - this file
    /tests- Contains test classes for testing diffent classes in the program using unit testing
    /src - folder which contains all source files for the program to run
    /bin - contains the compiled classes for all the source files and their test classes
    /javadoc- contains the documentation of the given source files
    /coverage - contains the coverage statistics for testing
    data.txt- file that contains the contents to be used when executing the program


DESIGN
------
The code taken from Weiss textbook was used to implement the graph and dijkstra's shortest path algorithm.
As a result the project contains other accompanying classes and these are Edge for the edges in a graph, Vertex and Path.
All of these classes are used together with the graph class SimulatorTwo.

The graph class contains some useful methods and a summary of them and their uses is given below.

 // SimulatorOne class: evaluate shortest paths.
        //
         // CONSTRUCTION: with no parameters.
        //
         // ******************PUBLIC OPERATIONS**********************
         // void addEdge( String v, String w, double cvw ) --> Add additional edge
         // void printPath( String w ) --> Print path after alg is run
         // void dijkstra( String s ) --> Single-source weighted shortest path
         // boolean processRequest(Scanner s, SimulatorTwo s) --> for printing paths and shortest paths of the request path
         // ******************ERRORS*********************************
         // Some error checking is performed to make sure that SimulatorOne is ok
         // and that SimulatorOne satisfies properties needed by each
         // algorithm. Exceptions are thrown if errors are detected.


In the src folder there is a Hospital class. This class enables us to create an object with important properties and these are
Number of beds in the hospital, The name of the group to which the hospital belongs to and the name of the hospital which is
used to access it from the graph. The class also contains getters and setters which are used for altering and returning the
properties of the hospital objects.

On running the SimulatorTwo class, the user is asked to insert an input in this form:
victim victim_number traffic_message node1 node2 cost

The traffic message can be delay, clear or normal only. If its normal the cost
between node1 and node2 is not changed. If there is a delay, cost is added to the normal cost of node1 and node2.
If it s clear, then a cost of 10 is subtracted from the normal cost between node1 and node2. This means that the cost is to provided
if and only if there is a delay in the traffic between node1 and node2.

The program will then calculate the shortest path to the victim from the given list of ambulances. It then gives an output with the following form:

Group_name hospital number: hospital_number Cost: Total_cost Number Of beds: Number_Of_beds

Group_name ----> is the name of the that the ambulance used the destination hospital belong to

hospital_number ----> The number of the hospital that is used in the graph to identify it

Total_cost ----> Is the cost that it takes to get to the destination from the victim

Number_Of_beds ----> Is the number of beds in the destination hospital


