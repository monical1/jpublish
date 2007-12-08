/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jakub
 */
public class TextInput extends InputWidget {

    private String type = "text";

    TextInput(String[] attrs) {
        Map m = new HashMap();
        if (attrs.length % 2 != 0) {
            throw new RuntimeException(
                    "Invalid number of arguments passed to constructor.");
        }
        for(int i = 0; i < attrs.length; i = i + 2){
            m.put(attrs[i],attrs[i+1]);
        }
        setAttr(m);
    }

    public TextInput() {

    }

    public String getType() {
        return type;
    }
}
