package university.data;

import java.util.*;
import java.util.stream.Collectors;
import university.enums.*;
import university.model.*;
public class Database {
    private final List<User>   users   = new ArrayList<>();
    private final List<Course> courses = new ArrayList<>();

    public Database() {
        seed();
    }

    public Optional<User> login(String email, String password) {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.checkPassword(password))
                .findFirst();
    }

    public Optional<User>   findUserById(int id)       { return users.stream().filter(u -> u.getId() == id).findFirst(); }
    public Optional<Course> findCourseById(String id)  { return courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst(); }

    public List<User>    getAllUsers()     { return Collections.unmodifiableList(users); }
    public List<Course>  getAllCourses()   { return Collections.unmodifiableList(courses); }

    public List<Student>  getStudents()   { return users.stream().filter(u -> u instanceof Student).map(u -> (Student)u).collect(Collectors.toList()); }
    public List<Teacher>  getTeachers()   { return users.stream().filter(u -> u instanceof Teacher).map(u -> (Teacher)u).collect(Collectors.toList()); }
    public List<Manager>  getManagers()   { return users.stream().filter(u -> u instanceof Manager).map(u -> (Manager)u).collect(Collectors.toList()); }

    public void addUser(User u)     { users.add(u); }
    public void addCourse(Course c) { courses.add(c); }

    public boolean removeUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }

    public List<Course> getOpenCourses() {
        return courses.stream().filter(Course::isOpen).collect(Collectors.toList());
    }

    private void seed() {
        Admin admin = new Admin("System", "Admin", "admin@uni.edu", "admin");
        users.add(admin);
        Professor prof = new Professor("Aisha", "Bekova", "bekova@uni.edu", "pass",
                Faculty.COMPUTER_SCIENCE, 350_000);
        prof.addPaper(new ResearchPaper("Deep Learning for Kazakh NLP",   "Bekova", "IEEE",    47));
        prof.addPaper(new ResearchPaper("Transformers for Low-Resource",   "Bekova", "ACL",     89));
        prof.addPaper(new ResearchPaper("Federated Learning at the Edge",  "Bekova", "IoT J.",  34));
        prof.addPaper(new ResearchPaper("Quantum-Resistant Cryptography",  "Bekova", "C&S",      3));
        users.add(prof);

        Teacher t = new Teacher("Dauren", "Seitkali", "seitkali@uni.edu", "pass",
                Faculty.COMPUTER_SCIENCE, 250_000, TeacherTitle.SENIOR_LECTOR);
        users.add(t);

        Manager mgr = new Manager("Nursultan", "Abenov", "abenov@uni.edu", "pass",
                Faculty.COMPUTER_SCIENCE, ManagerType.OFFICE_REGISTRAR);
        users.add(mgr);

        Student s1 = new Student("Arman",  "Ospanov", "ospanov@uni.edu", "pass", Faculty.COMPUTER_SCIENCE, 2);
        Student s2 = new Student("Zarina", "Akhmet",  "akhmet@uni.edu",  "pass", Faculty.ENGINEERING,      1);
        GraduateStudent gs = new GraduateStudent("Dinara", "Kenzhe", "kenzhe@uni.edu", "pass", Faculty.COMPUTER_SCIENCE);
        gs.addPaper(new ResearchPaper("Kazakh Sentiment Analysis", "Kenzhe", "IEEE Access", 5));
        try { gs.setSupervisor(prof); } catch (Exception ignored) {}
        users.add(s1); users.add(s2); users.add(gs);

        Course oop  = new Course("Object-Oriented Programming", 6, Faculty.COMPUTER_SCIENCE);
        Course algo = new Course("Algorithms & Data Structures", 5, Faculty.COMPUTER_SCIENCE);
        Course math = new Course("Discrete Mathematics",         4, Faculty.COMPUTER_SCIENCE);

        oop.addLesson(new university.model.Lesson(LessonType.LECTURE,  "Monday",    "09:00", "A101"));
        oop.addLesson(new university.model.Lesson(LessonType.PRACTICE, "Wednesday", "11:00", "Lab3"));
        algo.addLesson(new university.model.Lesson(LessonType.LECTURE, "Tuesday",   "10:00", "B202"));

        oop.setTeacher(t);   t.addCourse(oop);
        algo.setTeacher(t);  t.addCourse(algo);
        math.setTeacher(prof); prof.addCourse(math);

        oop.setOpen(true);
        algo.setOpen(true);

        courses.add(oop); courses.add(algo); courses.add(math);
    }
}
