/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpublish.newforms;

import org.jpublish.newforms.HiddenInput;
import junit.framework.TestCase;

/**
 *
 * @author jakub
 */
public class HiddenInputTest extends TestCase {
    
    public HiddenInputTest(String testName) {
        super(testName);
    }            

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testOverall(){
        HiddenInput hi = new HiddenInput();
        assertTrue(hi.isHidden());
        String expResult = "<input type=\"hidden\" id=\"id_sesseion_id\" name=\"sesseion_id\" value=\"85755\"/>";
        assertEquals(hi.render("sesseion_id" ,"85755","id_sesseion_id"),expResult);
    }

}
