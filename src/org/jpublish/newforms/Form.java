/*
 * Form.java
 *
 * Created on November 19, 2007, 9:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package org.jpublish.newforms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jakub@labath.org
 */
public class Form {

    protected final static String EMPTY_STRING = "";
    private Map data;
    private boolean validationRequested = false;
    private List errors = new ArrayList();

    public Form init(Map data) {
        setData(data);
        bindChildren();
        return this;
    }

    public Form init(HttpServletRequest request) {
        Enumeration en = request.getParameterNames();
        Map m = new HashMap();
        while (en.hasMoreElements()) {
            String param = (String) en.nextElement();
            String[] vals = request.getParameterValues(param);
            if (vals != null && vals.length == 1) {
                m.put(param, vals[0]);
            } else {
                m.put(param, vals);
            }
        }
        return init(m);
    }

    public boolean isValid() {
        setValidationRequested(true);
        collectAndSetErrors();
        return hasValidData();
    }

    protected boolean hasValidData() {
        boolean hasError = false;
        for (Iterator iter = getFormFields().iterator(); iter.hasNext() && hasError == false;) {
            Field f = (Field) iter.next();
            try {
                f.clean();
            } catch (ValidationError ex) {
                hasError = true;
            }
        }
        //if has error false run any custom validation that may be defined in the class
        for (Iterator iter = getFormValidators().iterator(); iter.hasNext() && hasError == false;) {
            Method m = (Method) iter.next();
            try {
                m.invoke(this, null);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                Throwable source = ex.getCause();
                if (source instanceof ValidationError) {
                    hasError = true;
                } else {
                    throw new RuntimeException(ex);
                }
            }
        }
        return !hasError;
    }

    public Map cleanData() {
        Map m = new HashMap();
        for (Iterator iter = getFormFields().iterator(); iter.hasNext();) {
            Field f = (Field) iter.next();
            try {
                m.put(f.getName(), f.clean());
            } catch (ValidationError ignore) {
                m.put(f.getName(), null);
            }
        }
        return m;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String asP() {
        final String P = "<p>";
        final String PE = "</p>";
        final String L = "<label for=\"";
        final String LL = "\">";
        final String LE = "</label>";
        final String EMSG = "<span id=\"";
        final String EMSG2 = "\">";
        final String EMSGE = "</span>";
        StringBuffer r = new StringBuffer(300);
        for (Iterator iter = getFormFields().iterator(); iter.hasNext();) {
            Field f = (Field) iter.next();
            if (!f.getWidget().isHidden()) {
                r.append(P);
                r.append(L);
                r.append(f.getId());
                r.append(LL);
                r.append(f.getLabel());
                r.append(LE);
                r.append(f.toString());
                if (isValidationRequested() && f.isError()) {
                    r.append(EMSG);
                    r.append("error_" + f.getId());
                    r.append(EMSG2);
                    r.append(f.getErrorMessage());
                    r.append(EMSGE);
                }
                r.append(PE);
            } else {
                r.append(f.toString());
            }
        }
        return r.toString();
    }

    protected void bindChildren() {
        for (Iterator iter = getFormFields().iterator(); iter.hasNext();) {
            Field f = (Field) iter.next();
            f.setValue((String) getData().get(f.getName()));
        }
    }

    private void collectAndSetErrors() {
        List e = getErrors();
        e.clear();
        for (Iterator iter = getFormFields().iterator(); iter.hasNext();) {
            Field f = (Field) iter.next();
            try {
                f.clean();
            } catch (ValidationError ex) {
                e.add(ex.getMessage());
                f.setError(true);
            }
        }
        //now collect errors from custom validators
        for (Iterator iter = getFormValidators().iterator(); iter.hasNext();) {
            Method m = (Method) iter.next();
            try {
                m.invoke(this, null);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalArgumentException ex) {
                throw new RuntimeException(ex);
            } catch (InvocationTargetException ex) {
                Throwable source = ex.getCause();
                if (source instanceof ValidationError) {
                    e.add(source.getMessage());
                } else {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private List getFormFields() {
        Class c = this.getClass();
        java.lang.reflect.Field k[] = c.getDeclaredFields();
        //System.out.println(k.length);
        //System.out.println(k);
        ArrayList l = new ArrayList();
        for (int i = 0; i < k.length; i++) {
            if (k[i].getType() == Field.class || k[i].getType().getSuperclass() == Field.class) {
                try {
                    l.add(k[i].get(this));
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return l;
    }

    public boolean isValidationRequested() {
        return validationRequested;
    }

    public void setValidationRequested(boolean validationRequested) {
        this.validationRequested = validationRequested;
    }

    public List getErrors() {
        return errors;
    }

    private List getFormValidators() {
        List l = new ArrayList();
        Class c = this.getClass();
        Method meths[] = c.getMethods();
        for (int i = 0; i < meths.length; i++) {
            Method m = meths[i];
            if (m.getName().startsWith("validate")) {
                l.add(m);
            }
        }
        return l;
    }
}
