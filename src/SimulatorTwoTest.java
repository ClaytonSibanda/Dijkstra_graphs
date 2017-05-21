import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by Clayza on 2017-05-20.
 */
public class SimulatorTwoTest {
    @org.junit.Test
    public void processRequest() throws Exception {
        SimulatorTwo s = new SimulatorTwo();
        Scanner sn =new Scanner(System.in);
        s.processRequest(sn,s);
    }

    @org.junit.Test
    public void addEdge() throws Exception {
        SimulatorTwo si = new SimulatorTwo();
       // Vertex v = new Vertex("2");

        assertEquals(si.vertexMap.size(),0);
        si.addEdge("2","3",20);
        assertNotEquals(si.vertexMap.size(),0);
        assertEquals((int)si.vertexMap.get("2").adj.get(0).cost,(int)20);


    }

    @org.junit.Test
    public void printPath() throws Exception {
        SimulatorTwo si = new SimulatorTwo();
        si.addEdge("2","3",20);
        si.addEdge("0","1",34);
        si.addEdge("1","2",87);
        assertEquals((int)si.printPath("2"),(int)si.INFINITY);
    }

    @org.junit.Test
    public void dijkstra() throws Exception {
        SimulatorTwo si = new SimulatorTwo();
        si.addEdge("2","3",20);
        si.addEdge("0","1",34);
        si.addEdge("1","2",87);
        si.addEdge("0","3",56);

        si.dijkstra("0");
        assertTrue(si.printPath("2")==121);
        assertTrue(si.printPath("3")==56);

    }

    @org.junit.Test
    public void getVertex() throws Exception{
        SimulatorTwo si = new SimulatorTwo();
        si.addEdge("2","3",20);
        si.addEdge("0","1",34);
        si.addEdge("1","2",87);
        Vertex v =si.getVertex("2");
        Vertex v1 =si.getVertex("0");
        assertTrue(v.equals(si.vertexMap.get("2")));
        assertEquals((int)v.adj.get(0).cost,20);
        assertNotEquals((int)v1.adj.get(0).cost,20);
    }

}