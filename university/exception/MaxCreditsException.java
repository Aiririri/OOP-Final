package university.exception;

public class MaxCreditsException extends Exception {
    public MaxCreditsException(int have, int adding) {
        super("Credit limit exceeded: have " + have + ", adding " + adding + " would exceed 21.");
    }
}
