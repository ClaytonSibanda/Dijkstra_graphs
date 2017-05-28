

import java.util.LinkedList;
import java.util.List;
import java.io.FileReader;

import java.util.*;

/**
 * @author  Clayton Sibanda
 * @version 1.0
 * @since   2017-05-21
 */
public class SimulatorTwo {
    public static final double INFINITY = Double.MAX_VALUE;
    private int number_of_nodes;
    private String hospitals[];
    private String ambulances[];
    private int number_of_hospitals;
    private int number_of_victims;
    private String names = "";


    Map<String, Vertex> vertexMap = new HashMap<>();
    private Map<String, ArrayList<String>> group = new HashMap<>();
    private ArrayList<Hospital> hos = new ArrayList<>();

    public static void main(String args[]) {
        System.out.println("Enter the victim number followed by a traffic message. The messages can only be delay,normal or clear. e.g victim 4 delay 4 5 69, this means there is a victim at 4 and there is a delay between 4 and 5 of 69");
        System.out.println("If the message is clear or normal only put the areas the time is only for delay");
        SimulatorTwo gr = new SimulatorTwo();
        int lastlist = 0;
        Vertex v = new Vertex("0");


        try {
            FileReader fr = new FileReader("../data.txt");
            Scanner br = new Scanner(fr);

            String line = "";
            int counter = 0;


            while (br.hasNextLine()) {
                line = br.nextLine();

                if (counter == 0) {
                    gr.number_of_nodes = Integer.parseInt(line);
                } else {
                    if (line.split(" ").length > 1 && (counter - 1) == Integer.parseInt(line.split(" ")[1]) && line.split(" ")[0].length() > 1) {
                        //then we have a vertex followed by a list of adjacent vertices and costs
                        String arr[] = line.split(" ");
                        ArrayList<String> cont = new ArrayList<>();
                        //gr.group.put(arr[0],);
                        for (int i = 1; i < arr.length - 1; i++) {
                            cont.add(arr[i]);
                        }
                        gr.group.put(arr[0], cont);
                        for (int i = 2; i < arr.length; i += 2) {
                            gr.addEdge(arr[1], arr[i], Double.parseDouble(arr[i + 1]));
                            lastlist = Integer.parseInt(arr[1]);


                        }

                    } else {
                        if (line.length() == 1 && (counter - 2) == lastlist) {
                            gr.number_of_hospitals = Integer.parseInt(line);

                        }
                        if ((counter - 3) == lastlist) {
                            int count = 5;
                            gr.hospitals = line.split(" ");
                            for (Map.Entry<String, ArrayList<String>> e : gr.group.entrySet()) {
                                if (e.getKey().equals("Others") == false) {

                                    for (int i = 0; i < e.getValue().size(); i++) {

                                        for (String s : gr.hospitals) {
                                            if (s.equals(e.getValue().get(i)))
                                                gr.hos.add(new Hospital(count - i, s, e.getKey()));
                                            count++;

                                        }

                                    }
                                }
                            }


                        }
                        if ((counter - 4) == lastlist) {
                            gr.number_of_victims = Integer.parseInt(line);

                        }
                        if ((counter - 5) == lastlist) {
                            gr.ambulances = line.split(" ");
                        }


                    }
                }
                counter++;


            }


        } catch (Exception e) {

            System.out.println("An error " + e + " occurred");

        }
          /*   for (Map.Entry<String, Vertex> entry : gr.vertexMap.entrySet()) {
                 String key = entry.getKey();
                 Vertex value = entry.getValue();
                 System.out.println("The key is "+ key);
                 System.out.println("The vertex is "+ value.adj);
                 for(Edge e:value.adj){
                     System.out.println("The cost is is "+ e.cost);
                 }
             }


             System.out.println("hospitals now");
             for (Hospital item:gr.hos) {
                 System.out.println(item.getNumber_of_beds()+"  "+ item.getName()+"  "+item.getGroupName());
             }*/

        Scanner in = new Scanner(System.in);
        while (processRequest(in, gr)) ;


    }


    /**
     * This is the process request method used to print out shortest paths and their costs
     * @param in, scanner used to read input from the user
     * @param  g, SimulatorTwo object
     * @return boolean
     */
    public static boolean processRequest(Scanner in, SimulatorTwo g) { //Hash maps to keep the hospitals and their costs
        Map<String, Double> costMap = new HashMap<>();
        Map<String, String> pathMap = new HashMap<>();
        try {
            System.out.print("Enter start node:");
            String vic = in.nextLine();
            double cost1, cost2;
            String path1, path2;
//Increases the cost of the edge if there is a delay on the road
            if (vic.split(" ").length > 2 && vic.split(" ")[2].equals("delay")) {
                Vertex v = g.getVertex(vic.split(" ")[3]);
                for (Edge e : v.adj) {
                    if (e.dest.name.equals(vic.split(" ")[4])) {
                        e.cost = e.cost + Integer.parseInt(vic.split(" ")[5]);
                    }
                }

            }
            // Reduces the cost of the edge if the road to the destination is clear
            if (vic.split(" ").length > 2 && vic.split(" ")[2].equals("clear")) {
                Vertex v = g.getVertex(vic.split(" ")[3]);
                for (Edge e : v.adj) {
                    //System.out.println(e.cost);
                    if (e.dest.name.equals(vic.split(" ")[4])) {
                        e.cost = e.cost - 20;
                    }
                    //System.out.println(e.cost);
                }

            }


            for (String h : g.hospitals) {
                for (String a : g.ambulances) {
                    cost1 = 0;
                    cost2 = 0;
                    path1 = "";
                    path2 = "";
                    g.dijkstra(a);
                    cost1 = g.printPath(vic.split(" ")[1]);
                    g.names = "";
                    path1 = g.printPath(g.vertexMap.get(vic.split(" ")[1]));
                    //System.out.println(path1);
                    g.dijkstra(vic.split(" ")[1]);
                    cost2 = g.printPath(h);
                    g.names = "";
                    path2 = g.printPath(g.vertexMap.get(h));
                    // System.out.println(path1+" path2 "+path2);


                   // System.out.println(" Cost is "+ (cost1+cost2));
                    //System.out.println("Path is "+ path1);
                    // if(path1!=" " && path2!=" " && !(cost1>=INFINITY) && !(cost2>=INFINITY)){
                    //calculating the cost
                    if(costMap.isEmpty() || (cost1+cost2)<=(Collections.min(costMap.values()))  ){
                    costMap.put(h, (cost1 + cost2));
                   // System.out.println(h +" "+(cost1+cost2));
                    pathMap.put(h, path1.substring(1) + path2.substring(2));
                    }
                    // }

                }
            }

            double minValueInMap = (Collections.min(costMap.values()));

            for (Map.Entry<String, Double> entry : costMap.entrySet()) {
                // Iterate through hashmap

               // System.out.println("In the printing loop now\n"+entry.getKey()+" "+entry.getValue());
                if (entry.getValue() == minValueInMap && minValueInMap < INFINITY) {

                    for (Hospital h : g.hos) {
                        if (h.getName().equals(entry.getKey())) {
                            System.out.println(h.getGroupName() + " hospital number: " + entry.getKey() + " Cost: " + entry.getValue() + " Number of beds left: " + h.getNumber_of_beds());
                            System.out.println(pathMap.get(entry.getKey()));
                        }

                        // Print the key with max value
                    }
                }



            }
            if (minValueInMap >= INFINITY) {
                System.out.println("Not accessible");
            }


        } catch (NoSuchElementException e) {
            System.out.println("Error " + e + " occured");
            return false;
        } catch (SimulatorTwoException e) {
            System.err.println(e);
        }
        return true;
    }

    /**
     * This is the addEdge method used to add Edges into the Linkedlist of each vertex
     * @param sourceName, name of the source
     * @param  destName, Name of the destintion node
     * @param cost, Cost between the two nodes source and destination
     *
     */
    public void addEdge(String sourceName, String destName, double cost) { //System.out.println("Source "+ sourceName+" dest "+destName+" cost "+cost);
        Vertex v = getVertex(sourceName);
        //System.out.println(v.toString());
        Vertex w = getVertex(destName);
        v.adj.add(new Edge(w, cost));
    }

    /**
     * This is the printPath method used to get the cost between different paths
     * @param  destName, name of the destination node
     * @return double
     */
    public double printPath(String destName) {
        Vertex w = vertexMap.get(destName);
        if (w == null)
            throw new NoSuchElementException();
        else if (w.dist == INFINITY)
            return INFINITY;
        else {
            //System.out.print( "(Cost is: " + w.dist + ") " );
            printPath(w);
            //System.out.println( );
            return w.dist;
        }

    }

    /**
     * This is the getVertex method that is used to return a particular vertex
     * @param  vertexName, name of the vertex to be returned
     * @return Vertex
     */
    public Vertex getVertex(String vertexName) {
        Vertex v = vertexMap.get(vertexName);
        if (v == null) {
            v = new Vertex(vertexName);
            vertexMap.put(vertexName, v);
        }
        return v;
    }

    /**
     * This is the printPath method that is used to return the paths from sources to destination nodes
     * @param  dest, Destination vertex
     * @return String
     */
    private String printPath(Vertex dest) {
        if (dest.prev != null) {
            printPath(dest.prev);
            // System.out.print( " to " );
        }
        names = names + " " + dest.name;
        //System.out.print(names );

        return names;

    }

    /**
     * This is the dijkstra method. It implements dijkstra's algorithm and is used to calculated the shortest path
     * @param  startName, name of the node to start iterating at
     */
    public void dijkstra(String startName) {
        PriorityQueue<Path> pq = new PriorityQueue<>();

        Vertex start = vertexMap.get(startName);
        if (start == null)
            throw new NoSuchElementException("Start vertex not found");

        clearAll();
        pq.add(new Path(start, 0));
        start.dist = 0;

        int nodesSeen = 0;
        while (!pq.isEmpty() && nodesSeen < vertexMap.size()) {
            Path vrec = pq.remove();
            Vertex v = vrec.dest;
            if (v.scratch != 0) // already processed v
                continue;

            v.scratch = 1;
            nodesSeen++;

            for (Edge e : v.adj) {
                Vertex w = e.dest;
                double cvw = e.cost;

                if (cvw < 0)
                    throw new SimulatorTwoException("SimulatorTwo  has negative edges");

                if (w.dist > v.dist + cvw) {
                    w.dist = v.dist + cvw;
                    w.prev = v;
                    pq.add(new Path(w, w.dist));
                }
            }
        }
    }

    /**
     * This is the clearAll() method and it is used to clear te vertexMap and make it empty
     */
    private void clearAll() {
        for (Vertex v : vertexMap.values())
            v.reset();
    }


}

// Used to signal violations of preconditions for
class SimulatorTwoException extends RuntimeException {
    public SimulatorTwoException(String name) {
        super(name);
    }
}

/**
 * Created by Clayza on 2017-05-13.
 */
// Represents an entry in the priority queue for Dijkstra's algorithm.
class Path implements Comparable<Path>
{
    public Vertex dest; // w
    public double cost; // d(w)

    public Path( Vertex d, double c )
    {
        dest = d;
        cost = c;
    }
    public int compareTo( Path rhs )
    {
        double otherCost = rhs.cost;

        return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
    }
}



/**
 * Created by Clayza on 2017-05-13.
 */
// Represents a vertex in the graph.
class Vertex
{
    public String name; // Vertex name
    public List<Edge> adj; // Adjacent vertices
    public double dist; // Cost
    public Vertex prev; // Previous vertex on shortest path
    public int scratch;// Extra variable used in algorithm

    public Vertex( String name )
    { this.name = name;
        this.adj = new LinkedList<Edge>( );

        reset( ); }

    public Vertex() {

    }

    public void reset( )
    { dist = SimulatorTwo.INFINITY; prev = null; /*pos = null;*/ scratch = 0; }
}

/**
 * Created by Clayza on 2017-05-13.
 */
// Represents an edge in the graph.
class Edge {
    public Vertex dest; // Second vertex in Edge
    public double cost; // Edge cost

    public Edge(Vertex d, double c) {
        dest = d;
        cost = c;
    }
}


/**
 * @author  Clayton Sibanda
 * @version 1.0
 * @since   2017-05-21
 */
 class Hospital {
    private int number_of_beds;
    private  String name;


    private String groupName;

    public Hospital(int number_of_beds, String name,String groupName) {
        this.number_of_beds = number_of_beds;
        this.name = name;
        this.groupName =groupName;
    }

    /**
     * setter method
     * @param name, name of the hospital
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method
     * @param groupName, name of the group to which the hospital/ambulance belongs to
     *
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * getter method
     * returns the number of beds in the hospital
     *
     */
    public int getNumber_of_beds() {
        return number_of_beds;
    }


    /**
     * getter method
     * returns the name of the hospital
     *
     */
    public String getName() {
        return name;
    }




    /**
     * getter method
     * returns the group name of the hospital
     *
     */
    public String getGroupName() {
        return groupName;
    }


}
