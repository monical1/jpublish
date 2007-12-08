/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

import org.jpublish.newforms.CharField;
import org.jpublish.newforms.Field;
import org.jpublish.newforms.ValidationError;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author jakub
 */
public class CharFieldTest extends TestCase {

    public static String EMPTY_STRING = "";

    public CharFieldTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(CharFieldTest.class);
        return suite;
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testOverall() throws ValidationError {
        //the original manual test that I had
        Field f = new CharField().init("myfieldX");
        assertEquals(f.getName(), "myfieldX");
        assertTrue(f.isRequired() == false);
        f.setValue(EMPTY_STRING);
        assertEquals(f.getValue(), EMPTY_STRING);
        f.setValue(null);
        assertNull(f.getValue());
        f.setValue("kk");
        assertEquals("kk", f.clean());
        f.setValue("   kk   ");
        assertEquals("kk", f.clean());
        f.setValue(EMPTY_STRING);
        assertEquals(EMPTY_STRING, f.clean());
        f.setValue(null);
        assertEquals(EMPTY_STRING, f.clean());
        Field f2 = new CharField().init("myfield", true);
        assertTrue(f2.isRequired());
        assertEquals(f2.getName(), "myfield");
        f2.setValue(EMPTY_STRING);
        try {
            f2.clean();
            throw new AssertionError("field is required");
        } catch (ValidationError ignore) {
        }
        f2.setValue(null);
        try {
            f2.clean();
            throw new AssertionError("field is required");
        } catch (ValidationError ignore) {
        }
        f2.setValue("   ");
        try {
            f2.clean();
            throw new AssertionError("field is required");
        } catch (ValidationError ignore) {
        }
        Field f3 = new CharField().init("third_field", true, "Missing");
        f3.setValue(EMPTY_STRING);
        try {
            f3.clean();
            throw new AssertionError("field is required");
        } catch (ValidationError ex) {
            assertEquals(ex.getMessage(), "Missing");
        }

    }
    
    public void testWidgetAndRendering() throws ValidationError {
        Field f = new CharField().init("test_field");
        String expResult = "<input type=\"text\" id=\"id_test_field\" name=\"test_field\" value=\"\"/>";
        assertEquals(f.toString(), expResult);
        f.setValue("foo");
        String expResult2 = "<input type=\"text\" id=\"id_test_field\" name=\"test_field\" value=\"foo\"/>";
        assertEquals(f.toString(), expResult2);
    }
    
}
