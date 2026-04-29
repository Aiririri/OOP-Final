package university.model;

import university.enums.Faculty;
import university.exception.LowHIndexException;
import java.util.*;

public class GraduateStudent extends Student implements Researcher {

    private final List<ResearchPaper> papers = new ArrayList<>();
    private Researcher supervisor;

    public GraduateStudent(String fn, String ln, String email, String pw, Faculty faculty) {
        super(fn, ln, email, pw, faculty, 4);
    }

    public void setSupervisor(Researcher sup) throws LowHIndexException {
        if (sup.getHIndex() < 3) {
            String name = (sup instanceof User u) ? u.getFullName() : "Unknown";
            throw new LowHIndexException(name, sup.getHIndex());
        }
        this.supervisor = sup;
    }

    public Researcher getSupervisor() { 
        return supervisor; 
    }

    @Override public List<ResearchPaper> getPapers() { 
        return Collections.unmodifiableList(papers); 
    }
    @Override public void addPaper(ResearchPaper p)  { 
        papers.add(p); 
    }
    @Override public String getRole() {
        return "Graduate Student (h-index: " + getHIndex() + ")";
    }
}
