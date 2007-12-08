package org.jpublish.newforms;

import org.jpublish.newforms.HiddenField;
import org.jpublish.newforms.CharField;
import org.jpublish.newforms.Form;
import org.jpublish.newforms.Field;

/*
 * TestForm.java
 *
 * Created on November 19, 2007, 11:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author jakub@labath.org
 */
public class TestForm extends Form {

    public Field first = new CharField().init("first","First name:", true);
    public Field second = new CharField().init("last","Last name:", true);
    public Field token = new HiddenField().init("token",true);
    public String g = "this is still a regular object like any other";
    public int jjj = 254;

}

