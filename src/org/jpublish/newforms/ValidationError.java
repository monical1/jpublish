/*
 * ValidationError.java
 *
 * Created on November 19, 2007, 10:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.jpublish.newforms;

/**
 *
 * @author jakub@labath.org
 */
public class ValidationError extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>ValidationError</code> without detail message.
     */
    public ValidationError() {
    }
    
    
    /**
     * Constructs an instance of <code>ValidationError</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ValidationError(String msg) {
        super(msg);
    }
}
