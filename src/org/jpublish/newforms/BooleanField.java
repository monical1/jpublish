/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

/**
 *
 * @author jakub
 */
public class BooleanField extends Field {

    private Widget widget = new CheckboxInput();

    public Object clean() throws ValidationError {
        Boolean ret = null;
        String r = defaultString(getValue(), EMPTY_STRING);
        if (isRequired() && isBlankString(r)) {
            throw new ValidationError(getErrorMessage());
        } else if ("true".equals(r)) {
            ret = new Boolean(true);
        } else {
            ret = new Boolean(false);
        }
        return ret;
    }

    public Widget getWidget() {
        return this.widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
