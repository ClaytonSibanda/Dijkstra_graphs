import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Clayza on 2017-05-20.
 */
public class VertexTest {
    @Test
    public void reset() throws Exception {
        Vertex v1 = new Vertex();
    Vertex v = new Vertex("0");
    assertNull(v.prev);
    assertNotEquals(v.dist,SimulatorTwo.INFINITY);

    }


}