package university.model;

import university.enums.Faculty;
import university.enums.TeacherTitle;

import java.util.*;

public class Teacher extends User {

    private TeacherTitle title;
    private Faculty      faculty;
    private double       salary;

    private final List<Course>         courses = new ArrayList<>();
    private final Map<String, Integer> ratings = new HashMap<>(); // studentId -> rating

    public Teacher(String fn, String ln, String email, String pw,
                   Faculty faculty, double salary, TeacherTitle title) {
        super(fn, ln, email, pw);
        this.title = title; this.faculty = faculty; this.salary = salary;
    }

    public void addCourse(Course c)    { if (!courses.contains(c)) courses.add(c); }
    public void removeCourse(Course c) { courses.remove(c); }

    public void putMark(Student s, Course c, double a1, double a2, double fin) {
        Mark m = new Mark(a1, a2, fin, getFullName());
        s.addMark(c.getCourseId(), m);
    }

    public void receiveRating(int studentId, int rating) {
        if (rating < 1 || rating > 5) throw new IllegalArgumentException("Rating must be 1-5.");
        ratings.put(studentId + "", rating);
    }

    public double getAverageRating() {
        return ratings.values().stream().mapToInt(i->i).average().orElse(0);
    }

    public TeacherTitle getTitle()   { return title; }
    public Faculty      getFaculty() { return faculty; }
    public double       getSalary()  { return salary; }
    public List<Course> getCourses() { return Collections.unmodifiableList(courses); }

    public void setTitle(TeacherTitle t)  { title   = t; }
    public void setFaculty(Faculty f)     { faculty = f; }
    public void setSalary(double s)       { salary  = s; }

    @Override public String getRole() { return "Teacher (" + title + ")"; }
}
