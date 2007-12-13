/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author jakub
 */
public class CheckboxInput extends Widget {

    public CheckboxInput(String[] attrs) {
        Map m = new HashMap();
        if (attrs.length % 2 != 0) {
            throw new RuntimeException(
                    "Invalid number of arguments passed to constructor.");
        }
        for (int i = 0; i < attrs.length; i = i + 2) {
            m.put(attrs[i], attrs[i + 1]);
        }
        setAttr(m);
    }

    public CheckboxInput() {
    }

    public String render(String name, String value, String id) {
        StringBuffer sb = new StringBuffer(40);
        sb.append("<input type=\"checkbox\"");
        sb.append(" id=");
        sb.append(quoteAndEscapeStr(id));
        sb.append(" name=");
        sb.append(quoteAndEscapeStr(name));
        sb.append(" value=\"true\"");
        for (Iterator iter = getAttr().keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            sb.append(" ");
            sb.append(key);
            sb.append("=");
            sb.append(quoteAndEscapeStr((String) getAttr().get(key)));
        }
        if("true".equals(value)){
            sb.append(" checked=\"checked\" ");
        }
        sb.append("/>");
        return sb.toString();
    }

    public boolean isHidden() {
        return false; //never hidden
    }
}
