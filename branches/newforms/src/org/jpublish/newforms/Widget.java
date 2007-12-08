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
public abstract class Widget {
    protected static final String EMPTY_STRING = "";
    protected static final String EMPTY_VALUE = "\"\"";
    protected static final String DOUBLE_QUOTE = "\"";
    private Map attr = new HashMap();
    
    public abstract String render (String name, String value, String id);
    
    protected String quoteAndEscapeStr(String s){
        if(s == null) {
            return EMPTY_VALUE;
        }
        StringBuffer sb = new StringBuffer(s.length()+2);
        sb.append(DOUBLE_QUOTE);
        sb.append(s); //TODO actual escaping
        sb.append(DOUBLE_QUOTE);
        return sb.toString();
    }
    
    public abstract boolean isHidden();
    
    public Map getAttr() {
        return attr;
    }

    public void setAttr(Map attr) {
        this.attr = attr;
    }

}
