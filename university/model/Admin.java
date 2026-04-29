package university.model;
import java.util.*;

public class Admin extends User {
    private final List<String> logs = new ArrayList<>();
    public Admin(String fn, String ln, String email, String pw) {
        super(fn, ln, email, pw);
    }
    public void log(String msg) { 
        logs.add(msg); 
    }
    public List<String> getLogs() { 
        return Collections.unmodifiableList(logs); 
    }
    @Override public String getRole() { 
        return "Admin"; 
    }
}
