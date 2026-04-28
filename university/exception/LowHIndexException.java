package university.exception;

public class LowHIndexException extends Exception {
    public LowHIndexException(String name, int h) {
        super("'" + name + "' cannot be supervisor: h-index=" + h + " (need >= 3).");
    }
}
