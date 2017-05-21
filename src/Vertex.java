import java.util.LinkedList;
import java.util.List;

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
