/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class CheckboxInputTest extends TestCase {

    public CheckboxInputTest(String testName) {
        super(testName);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of render method, of class CheckboxInput.
     */
    public void testRender() {
        System.out.println("render");
        String name = "smilk";
        String value = "false";
        String id = "id_smilk";
        CheckboxInput instance = new CheckboxInput();
        String expResult = "<input type=\"checkbox\" id=\"id_smilk\" name=\"smilk\" value=\"true\"/>";
        String result = instance.render(name, value, id);
        assertEquals(expResult, result);
        value = "true";
        result = instance.render(name, value, id);
        expResult = "<input type=\"checkbox\" id=\"id_smilk\" name=\"smilk\" value=\"true\" checked=\"checked\" />";
        assertEquals(expResult, result);
    }

    /**
     * Test of isHidden method, of class CheckboxInput.
     */
    public void testIsHidden() {
        CheckboxInput instance = new CheckboxInput();
        boolean expResult = false;
        boolean result = instance.isHidden();
        assertEquals(expResult, result);
    }
}
