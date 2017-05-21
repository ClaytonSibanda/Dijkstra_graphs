import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Clayza on 2017-05-20.
 */
public class HospitalTest {
    @Test
    public void getNumber_of_beds() throws Exception {
        Hospital h = new Hospital(23,"4","MediCross");
        Hospital h1 = new Hospital(32,"3","MediClinic");
        assertEquals("This test Should fail",32,h.getNumber_of_beds());
        assertNotEquals(h1,h);
        assertFalse(h.getName().equals(4));
        assertNotNull(h);
    }

    @Test
    public void getName() throws Exception {
        Hospital h = new Hospital(78,"4","MediCross");
        Hospital h1 = new Hospital(32,"3","MediClinic");
        assertEquals("This test Should fail","4",h.getName());
        assertNotEquals(h1,h);
        h.setName("89");
        assertTrue(h.getName().equals("89"));
        assertNotNull(h);
    }

    @Test
    public void getGroupName() throws Exception {
        Hospital h = new Hospital(97,"4","MediCross");
        Hospital h1 = new Hospital(55,"3","MediClinic");
        assertEquals("This test Should fail","MediCross",h.getGroupName());
        assertNotEquals(h1,h);
        h.setGroupName("Clayza");
        assertFalse(h.getName().equals("MediCross"));
        assertNotNull(h);
    }

}