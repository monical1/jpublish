/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpublish.newforms;

import org.jpublish.newforms.HiddenField;
import org.jpublish.newforms.Widget;
import org.jpublish.newforms.HiddenInput;
import org.jpublish.newforms.Field;
import org.jpublish.newforms.ValidationError;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class HiddenFieldTest extends TestCase {
    
    public HiddenFieldTest(String testName) {
        super(testName);
    }            

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of clean method, of class HiddenField.
     */
    public void testClean() throws ValidationError {
        Field instance = new HiddenField().init("test_field");
        Object expResult = "";
        Object result = instance.clean();
        assertEquals(expResult, result);
        instance.setValue("john ");
        assertEquals("john",instance.clean());
        Field f1 = new HiddenField().init("test_field",true);
        f1.setValue("");
        try {
            f1.clean();
            throw new AssertionError("field is required");
        } catch (ValidationError ignore) {
        }
    }

    /**
     * Test of getWidget method, of class HiddenField.
     */
    public void testGetWidget() {
        HiddenField instance = new HiddenField();
        Widget expResult = new HiddenInput();
        Widget result = instance.getWidget();
        assertEquals(expResult.getClass(), result.getClass());
        assertTrue(result.isHidden());
    }



}
