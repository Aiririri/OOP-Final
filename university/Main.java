package university;

import university.auth.AuthService;
import university.enums.ManagerType;
import university.enums.TeacherTitle;
import university.exception.AuthenticationException;
import university.factory.UserFactory;
import university.model.academic.Course;
import university.model.research.ResearchPaper;
import university.model.research.ResearchProject;
import university.model.user.*;
import university.storage.DataStore;
import university.ui.*;

import java.util.Arrays;
import java.util.Date;

import static university.ui.ConsoleUtils.*;

/**
 * University Information System — entry point.
 * Design patterns used:
 *  1. Singleton — DataStore   (central persistent storage)
 *  2. Singleton — AuthService (session / authentication)
 *  3. Factory   — UserFactory (role-based object creation)
 *  4. Strategy  — Comparator<ResearchPaper> (interchangeable sort orders)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println();
        System.out.println("   UNIVERSITY INFORMATION SYSTEM  ");


        seedDefaultData();

        boolean running = true;
        while (running) {
            System.out.println("  Default accounts:");
            System.out.println("  | Username    | Password | Role              |");
            System.out.println("  | admin       | admin123 | Admin             |");
            System.out.println("  | manager1    | pass123  | Manager (OR)      |");
            System.out.println("  | professor1  | pass123  | Professor         |");
            System.out.println("  | lecturer1   | pass123  | Lecturer          |");
            System.out.println("  | senior1     | pass123  | Senior Lecturer   |");
            System.out.println("  | student1    | pass123  | Student  Year 2   |");
            System.out.println("  | student2    | pass123  | Student  Year 4   |");
            System.out.println("  | student3    | pass123  | Student  Year 1   |");
            header("MAIN MENU");
            System.out.println("  [1] Login");
            System.out.println("  [0] Exit");
            line();
            int choice = readInt("  -> ");

            if (choice == 0) {
                running = false;
            } else if (choice == 1) {
                String username = readString("  Username: ");
                String password = readString("  Password: ");
                try {
                    User user = AuthService.getInstance().authenticate(username, password);
                    System.out.println("  Welcome, " + user.getFullName() + "!");
                    routeToMenu(user);
                    AuthService.getInstance().logout();
                } catch (AuthenticationException e) {
                    System.out.println("  [!] " + e.getMessage());
                }
            }
        }
        System.out.println("  System shut down. Goodbye!");
    }

    private static void routeToMenu(User user) {
        if      (user instanceof Admin)   new AdminUI((Admin)   user).show();
        else if (user instanceof Manager) new ManagerUI((Manager) user).show();
        else if (user instanceof Teacher) new TeacherUI((Teacher) user).show();
        else if (user instanceof Student) new StudentUI((Student) user).show();
        else System.out.println("  Unknown user role.");
    }

    private static void seedDefaultData() {
        DataStore ds = DataStore.getInstance();
        if (ds.getUserByUsername("admin") != null) return;

        System.out.println("[System] First launch - default accounts");

        Admin admin = UserFactory.createAdmin(
            "admin", "admin123", "System", "Admin",
            "admin@university.edu", 80_000);
        ds.saveUser(admin);

        Manager manager = UserFactory.createManager(
            "manager1", "pass123", "Dwayne", "Johnson",
            "Dwayne@university.edu", 70_000, ManagerType.OR);
        ds.saveUser(manager);

        Teacher professor = UserFactory.createTeacher(
            "professor1", "pass123", "Robert", "DauniJr",
            "rDauni@university.edu", 95_000, TeacherTitle.PROFESSOR);
        ds.saveUser(professor);

        Teacher lecturer = UserFactory.createTeacher(
            "lecturer1", "pass123", "Zhangir", "Jojo",
            "Zhanjojo@university.edu", 55_000, TeacherTitle.LECTURER);
        ds.saveUser(lecturer);

        Teacher senior = UserFactory.createTeacher(
            "senior1", "pass123", "Alikhan", "Xan",
            "Xan@university.edu", 65_000, TeacherTitle.SENIOR_LECTURER);
        ds.saveUser(senior);

        Student student1 = UserFactory.createStudent(
            "student1", "pass123", "Michael", "Jackson",
            "jackson@student.edu", 2);
        ds.saveUser(student1);

        Student student2 = UserFactory.createStudent(
            "student2", "pass123", "Messi", "Ronaldo",
            "Messi@student.edu", 4);
        ds.saveUser(student2);

        Student student3 = UserFactory.createStudent(
            "student3", "pass123", "Mine", "Craft",
            "Minecraft@student.edu", 1);
        ds.saveUser(student3);

        Course oop = new Course("Object-Oriented Programming", 3, 30, 2,
            university.enums.StudentDegree.BACHELOR);
        Course db  = new Course("History of Kazakhstan", 4, 25, 3,
            university.enums.StudentDegree.BACHELOR);
        Course ml  = new Course("Machine Learning", 5, 20, 4,
            university.enums.StudentDegree.BACHELOR);
        ds.saveCourse(oop);
        ds.saveCourse(db);
        ds.saveCourse(ml);

        manager.assignCourseToTeacher(oop, lecturer);
        manager.assignCourseToTeacher(db,  senior);
        manager.assignCourseToTeacher(ml,  professor);

        ResearchPaper paper1 = new ResearchPaper(
            "Deep Learning for Student Performance Prediction",
            Arrays.asList("Robert DauniJr", "Dwayne Johnson"),
            "IEEE Transactions on Education",
            "10.1109/TE.2022.9766691",
            12, 47, new Date(),
            Arrays.asList("deep learning", "education", "prediction"),
            "We propose a deep-learning framework for predicting student outcomes.");
        professor.addResearchPaper(paper1);

        ResearchPaper paper2 = new ResearchPaper(
            "Adaptive Assessment Systems in Modern Universities",
            Arrays.asList("Robert DauniJr"),
            "Journal of Educational Technology",
            "10.1016/j.jedt.2023.01.002",
            8, 23, new Date(),
            Arrays.asList("adaptive", "assessment", "AI"),
            "A review of current adaptive assessment approaches.");
        professor.addResearchPaper(paper2);

        ResearchProject project = new ResearchProject("AI in Education", new Date());
        ds.saveProject(project);
        try { professor.joinProject(project); } catch (Exception ignored) {}

        student2.setActiveResearcher(true);
        try { student2.setSupervisor(professor); } catch (Exception ignored) {}

        ds.save();
        System.out.println("[System] Done.");
        System.out.println();
        System.out.println();
    }
}
