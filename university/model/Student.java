package university.model;

import university.enums.Faculty;
import university.exception.MaxCreditsException;
import university.exception.MaxFailsException;

import java.util.*;

public class Student extends User {

    private Faculty faculty;
    private int     year;

    private final List<Course>         courses    = new ArrayList<>();
    private final Map<String, Mark>    marks      = new LinkedHashMap<>();
    private final Map<String, Integer> failCounts = new HashMap<>();

    public Student(String fn, String ln, String email, String pw, Faculty faculty, int year) {
        super(fn, ln, email, pw);
        this.faculty = faculty;
        this.year    = year;
    }

    public void registerCourse(Course course) throws MaxCreditsException, MaxFailsException {
        if (failCounts.getOrDefault(course.getCourseId(), 0) >= 3)
            throw new MaxFailsException(getFullName(), course.getName());
        int total = courses.stream().mapToInt(Course::getCredits).sum();
        if (total + course.getCredits() > 21)
            throw new MaxCreditsException(total, course.getCredits());
        if (!courses.contains(course)) {
            courses.add(course);
            course.enroll(this);
        }
    }

    public void dropCourse(Course course) {
        if (courses.remove(course)) course.unenroll(this);
    }

    public void addMark(String courseId, Mark m) {
        marks.put(courseId, m);
        if (!m.passed()) failCounts.merge(courseId, 1, Integer::sum);
    }

    public double getGpa() {
        if (marks.isEmpty()) return 0;
        return marks.values().stream().mapToDouble(Mark::toGradePoint).average().orElse(0);
    }

    public int getCurrentCredits() {
        return courses.stream().mapToInt(Course::getCredits).sum();
    }

    public void printTranscript() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  TRANSCRIPT — " + getFullName());
        System.out.printf("  Faculty: %s  |  Year: %d  |  GPA: %.2f%n", faculty, year, getGpa());
        System.out.println("=".repeat(60));
        if (marks.isEmpty()) { System.out.println("  No marks yet."); }
        else {
            System.out.printf("  %-12s %-6s %-6s %-6s %-6s %s%n",
                    "Course", "ATT1", "ATT2", "FIN", "TOT", "GRD");
            System.out.println("  " + "-".repeat(48));
            marks.forEach((id, m) -> System.out.printf(
                    "  %-12s %-6.1f %-6.1f %-6.1f %-6.1f %s%n",
                    id, m.getAtt1(), m.getAtt2(), m.getFinalExam(), m.getTotal(), m.letterGrade()));
        }
        System.out.println("=".repeat(60));
    }

    public Faculty          getFaculty()   { return faculty; }
    public int              getYear()      { return year; }
    public List<Course>     getCourses()   { return Collections.unmodifiableList(courses); }
    public Map<String,Mark> getMarks()     { return Collections.unmodifiableMap(marks); }

    public void setFaculty(Faculty f) { faculty = f; }
    public void setYear(int y)        { year = y; }

    @Override public String getRole() { return "Student (Yr " + year + ")"; }
}
