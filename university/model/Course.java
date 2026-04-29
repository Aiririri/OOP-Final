package university.model;
import university.enums.Faculty;
import java.util.*;

public class Course {
    private static int nextId = 1;
    private final String  courseId;
    private String  name;
    private int     credits;
    private Faculty faculty;
    private boolean open = false;
    private final List<Teacher> teachers = new ArrayList<>();
    private final List<Lesson>  lessons  = new ArrayList<>();
    private final List<Student> enrolled = new ArrayList<>();

    public Course(String name, int credits, Faculty faculty) {
        this.courseId = "C" + nextId++;
        this.name = name; this.credits = credits; this.faculty = faculty;
    }

    public void unenroll(Student s) { 
        enrolled.remove(s); 
    }

    public boolean enroll(Student s) {
        if (!enrolled.contains(s)) { 
            enrolled.add(s); return true; 
        }
        return false;
    }

    public void addLesson(Lesson l)    { lessons.add(l); }

    public String   getCourseId()      { return courseId; }
    public String   getName()          { return name; }
    public int      getCredits()       { return credits; }
    public Faculty  getFaculty()       { return faculty; }
    public boolean  isOpen()           { return open; }
    public Teacher  getTeacher()       { return teacher; }
    public List<Lesson>  getLessons()  { return Collections.unmodifiableList(lessons); }
    public List<Student> getEnrolled() { return Collections.unmodifiableList(enrolled); }

    public void setName(String n)      { name = n; }
    public void setOpen(boolean o)     { open = o; }
    public void setTeacher(Teacher t)  { teacher = t; }

    @Override public boolean equals(Object o) {
        return o instanceof Course c && courseId.equals(c.courseId);
    }
    @Override public int hashCode() { 
        return courseId.hashCode(); 
    }

    @Override public String toString() {
        String t = teacher != null ? teacher.getFullName() : "TBA";
        return String.format("[%s] %-30s %d cr  Teacher: %-20s %s",
                courseId, name, credits, t, open ? "[OPEN]" : "[CLOSED]");
    }
}
