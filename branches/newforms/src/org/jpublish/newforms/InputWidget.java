/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jpublish.newforms;

import java.util.Iterator;

/**
 *
 * @author jakub
 */
public abstract class InputWidget extends Widget {
    
    public abstract String getType();
    
    public String render (String name, String value, String id){
        StringBuffer sb = new StringBuffer(40);
        sb.append("<input type=");
        sb.append(quoteAndEscapeStr(getType()));
        sb.append(" id=");
        sb.append(quoteAndEscapeStr(id));
        sb.append(" name=");
        sb.append(quoteAndEscapeStr(name));
        sb.append(" value=");
        sb.append(quoteAndEscapeStr(value));
        for (Iterator iter = getAttr().keySet().iterator();iter.hasNext();){
            String key = (String) iter.next();
            sb.append(" ");
            sb.append(key);
            sb.append("=");
            sb.append(quoteAndEscapeStr( (String) getAttr().get(key)));
        }
        sb.append("/>");
        return sb.toString();
    }
    
    public boolean isHidden(){
        return "hidden".equalsIgnoreCase(getType().trim());
    }

}
