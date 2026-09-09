package testIdentifier;

public class Identifier {

    public boolean validateIdentifier(String s) {
        boolean valid_id = false;

        if (s.length() > 0) {
            valid_id = true;
        }
        if (valid_id && (s.length() >= 1) && (s.length() <= 6))
            return true;
        else
            return false;
    }

}
