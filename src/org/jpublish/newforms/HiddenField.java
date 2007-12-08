/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jpublish.newforms;

/**
 *
 * @author jakub
 */
public class HiddenField extends Field {

    Widget widget = new HiddenInput();

    public Object clean() throws ValidationError {
        String r = defaultString(getValue(),EMPTY_STRING);
        if (isRequired() && isBlankString(r)) {
            throw new ValidationError(getErrorMessage());
        }
        return r.trim();
    }

    public Widget getWidget() {
        return widget;
    }

    public void setWidget(Widget widget) {
        this.widget = widget;
    }
}
