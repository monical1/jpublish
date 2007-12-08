/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpublish.newforms;

import org.jpublish.newforms.TextInput;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class TextInputTest extends TestCase {
    
    public TextInputTest(String testName) {
        super(testName);
    }            

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getType method, of class TextInput.
     */
    public void testGetType() {
        TextInput instance = new TextInput();
        assertEquals("text", instance.getType());
    }
    
    public void testRender(){
        TextInput instance = new TextInput();
        String result = instance.render("first_name", "Tom","id_first_name");
        assertEquals("<input type=\"text\" id=\"id_first_name\" name=\"first_name\" value=\"Tom\"/>", result);
    }
    
    public void testOverAll(){
        TextInput instance = new TextInput();
        assertFalse(instance.isHidden());
        TextInput ti = new TextInput(new String[]{"class","someclass","size","60"});
        String expresult = "<input type=\"text\" id=\"id_full_name\" name=\"full_name\" value=\"\" class=\"someclass\" size=\"60\"/>";
        assertEquals(ti.render("full_name", "","id_full_name"), expresult);
    }

}
