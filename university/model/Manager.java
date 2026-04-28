package university.model;

import university.enums.Faculty;
import university.enums.ManagerType;

import java.util.*;

public class Manager extends User {

    private ManagerType type;
    private Faculty     faculty;
    private final List<String> news = new ArrayList<>();

    public Manager(String fn, String ln, String email, String pw, Faculty faculty, ManagerType type) {
        super(fn, ln, email, pw);
        this.faculty = faculty; this.type = type;
    }

    public void publishNews(String headline) {
        news.add(headline);
    }

    public List<String> getNews() { return Collections.unmodifiableList(news); }

    public ManagerType getType()     { return type; }
    public Faculty     getFaculty()  { return faculty; }

    @Override public String getRole() { return "Manager (" + type + ")"; }
}
