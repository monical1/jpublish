/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpublish.newforms;

import org.jpublish.newforms.Form;
import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class FormTest extends TestCase {
    
    public FormTest(String testName) {
        super(testName);
    }            

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of isValid method, of class Form.
     */
    public void testIsValid() {
        Form instance = new TestForm().init(new HashMap());
        boolean expResult = false;
        boolean result = instance.isValid();
        assertEquals(expResult, result);
        Map m = new HashMap();
        m.put("first","Jakub");
        m.put("last","Labath");
        m.put("token","48575636");
        Form f1 = new TestForm().init(m);
        assertEquals(true, f1.isValid());
    }

    /**
     * Test of cleanData method, of class Form.
     */
    public void testCleanData() {
        Map data = new HashMap();
        Form f = new TestForm().init(data);
        Map result = f.cleanData();
        data.put("first", null);
        data.put("last", null);
        data.put("token", null);
        assertEquals(data, result);
        data.put("first", " Jakub ");
        data.put("last", "Labath");
        data.put("token", null);
        Form f1 = new TestForm().init(data);
        Map result2 = f1.cleanData();
        data.put("first", "Jakub");
        assertEquals(data, result2);
        data.put("token", "454353534534");
        Form f2 = new TestForm().init(data);
        Map result3 = f2.cleanData();
        assertEquals(data, result3);
    }

    /**
     * Test of setGetData method, of class Form.
     */
    public void testSetGetData() {
        Map data = new HashMap();
        Form instance = new TestForm();
        instance.setData(null);
        assertNull(instance.getData());
        instance.setData(data);
        assertEquals(data, instance.getData());
    }
    
    public void testAsP(){
        Map data = new HashMap();
        data.put("first", " Jakub ");
        data.put("last", "Labath");
        data.put("token", "55346546");
        Form f = new TestForm().init(data);
        String expResult = "<p><label for=\"id_first\">First name:</label><input type=\"text\" id=\"id_first\" name=\"first\" value=\" Jakub \"/></p><p><label for=\"id_last\">Last name:</label><input type=\"text\" id=\"id_last\" name=\"last\" value=\"Labath\"/></p><input type=\"hidden\" id=\"id_token\" name=\"token\" value=\"55346546\"/>";
        assertEquals(f.asP(),expResult);
    }
    public void testAsPWithError(){
        Map data = new HashMap();
        data.put("first", "  ");
        data.put("last", "Labath");
        data.put("token", "55346546");
        Form f = new TestForm().init(data);
        assertFalse(f.isValid());
        String expResult = "<p><label for=\"id_first\">First name:</label><input type=\"text\" id=\"id_first\" name=\"first\" value=\"  \"/><span id=\"error_id_first\">Field is required.</span></p><p><label for=\"id_last\">Last name:</label><input type=\"text\" id=\"id_last\" name=\"last\" value=\"Labath\"/></p><input type=\"hidden\" id=\"id_token\" name=\"token\" value=\"55346546\"/>";
        assertEquals(f.asP(),expResult);
    }
    
    public void testAsPWithMissingHidden(){
        Map data = new HashMap();
        data.put("first", "Jakub");
        data.put("last", "Labath");
        Form f = new TestForm().init(data);
        System.out.println(f.asP());
        assertFalse(f.isValid());
        assertFalse(((TestForm) f).first.isError());
        assertFalse(((TestForm) f).second.isError());
        assertTrue(((TestForm) f).token.isError());
    }

}
