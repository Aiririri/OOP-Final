package university.exception;

public class MaxFailsException extends Exception {
    public MaxFailsException(String student, String course) {
        super(student + " failed '" + course + "' 3 times. Academic suspension required.");
    }
}
