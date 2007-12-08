/*
 * Field.java
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
public abstract class Field {

    protected static String EMPTY_STRING = "";
    protected static String DEFAULT_ERROR = "Field is required.";
    private String value = EMPTY_STRING;
    private String name = EMPTY_STRING;
    private String errorMessage = EMPTY_STRING;
    private boolean required = false;
    private String label = EMPTY_STRING;
    private boolean error = false;

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String toString() {
        return getWidget().render(getName(), getValue(), getId());
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getId() {
        return "id_" + getName();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Field init(String name) {
        return init(name, getLabel(), getWidget());
    }
    
    public Field init(String name,boolean required) {
        return init(name, getLabel(), getWidget(), required);
    }
    
    public Field init(String name, String label) {
        return init(name, label, getWidget());
    }

    public Field init(String name, String label, Widget widget) {
        return init(name, label, widget, false);
    }

    public Field init(String name, String label, boolean required) {
        return init(name, label, getWidget(), required);
    }

    public Field init(String name, String label, Widget widget, boolean required) {
        return init(name, label,widget, required, DEFAULT_ERROR);
    }

    public Field init(String name, String label, boolean required, String err) {
        return init(name, label,getWidget(), required, err);
    }
    
    public Field init(String name, boolean required, String err) {
        return init(name, getLabel(),getWidget(), required, err);
    }

    public Field init(String name, String label,Widget widget, boolean required, String errorMessage) {
        setName(name);
        setLabel(label);
        setWidget(widget);
        setRequired(required);
        setErrorMessage(errorMessage);
        return this;
    }

    public static boolean isBlankString(String s) {
        if (s == null) {
            return true;
        }
        return EMPTY_STRING.equals(s.trim());
    }

    public static String defaultString(String s, String def) {
        if (s == null) {
            return def;
        } else {
            return s;
        }
    }

    public abstract Object clean() throws ValidationError;

    public abstract Widget getWidget();

    public abstract void setWidget(Widget widget);

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
