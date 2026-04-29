package university.model;

import university.enums.Faculty;
import university.enums.TeacherTitle;

import java.util.*;

public class Professor extends Teacher implements Researcher {

    private final List<ResearchPaper> papers = new ArrayList<>();

    public Professor(String fn, String ln, String email, String pw, Faculty faculty, double salary) {
        super(fn, ln, email, pw, faculty, salary, TeacherTitle.PROFESSOR);
    }

   