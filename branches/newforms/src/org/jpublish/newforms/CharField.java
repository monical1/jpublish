/*
 * CharField.java
 *
 * Created on November 19, 2007, 10:37 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.jpublish.newforms;

/**
 *
 * @author jakub@labath.org
 */
public class CharField extends Field {

    private Widget widget = new TextInput();
    
    public Object clean() throws ValidationError {
        String r = defaultString(getValue(),EMPTY_STRING);
        if (isRequired() && isBlankString(r)) {
            throw new ValidationError(getErrorMessage());
        }
        return r.trim();
    }

    public Widget getWidget() {
        return this.widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }

}
