import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Clayza on 2017-05-20.
 */
public class PathTest {
    @Test
    public void compareTo() throws Exception {
        Vertex v = new Vertex("0");
        Path p = new Path(v,45);
        Path p1 = new Path(v,56);
       assertTrue(0== p.compareTo(p));
       assertEquals(-1,p.compareTo(p1));


    }

}