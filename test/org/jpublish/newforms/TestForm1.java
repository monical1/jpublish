package org.jpublish.newforms;

/**
 *
 * @author jakub@labath.org
 */
public class TestForm1 extends Form {

    public Field first = new CharField().init("first", "First name:", true);
    public Field second = new CharField().init("last", "Last name:", true);

    public void validateFirst() throws ValidationError {
        String firstData = (String) cleanData().get("first");
        if (firstData != null) {
            if (firstData.length() > 0) {
                char c = firstData.charAt(0);
                if (Character.isUpperCase(c)) {
                    //all good return
                    return;
                }
            }
        }
        throw new ValidationError(
                "First letter of the first name must be uppercase.");
    }

    public void validateNames() throws ValidationError {
        String firstData = (String) cleanData().get("first");
        String lastData = (String) cleanData().get("last");
        if (firstData != null && lastData != null) {
            if (!firstData.matches(".*\\d+.*")) {
                if (!lastData.matches(".*\\d+.*")) {
                    return; //all good
                }
            }
        }
        throw new ValidationError("Names cannot contain digits.");
    }
}

