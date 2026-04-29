package university;
import university.data.Database;
import university.model.*;
import university.ui.*;
public class Main {
    public static void main(String[] args) {
        Database db = new Database();


        System.out.println("UNIVERSITY INFORMATION SYSTEM");

        System.out.println(" Demo accounts (email / password):        ");
        System.out.println("admin@uni.edu      / admin               ");
        System.out.println("bekova@uni.edu     / pass  (Professor)   ");
        System.out.println("seitkali@uni.edu   / pass  (Teacher)     ");
        System.out.println("abenov@uni.edu     / pass  (Manager)     ");
        System.out.println("ospanov@uni.edu    / pass  (Student Y2)  ");
        System.out.println("kenzhe@uni.edu     / pass  (Graduate)    ");


        while (true) {

            System.out.println("  LOGIN  (type 'quit' to exit)");

            String email = Input.line("Email");
            if (email.equalsIgnoreCase("quit")) {
                System.out.println("\n  Goodbye!\n");
                break;
            }
            String password = Input.line("Password");

            var result = db.login(email, password);
            if (result.isEmpty()) {
                System.out.println("  Wrong email or password. Try again.");
                continue;
            }

            User user = result.get();
            System.out.println("\n  Welcome, " + user.getFullName() + "! (" + user.getRole() + ")");

            if (user instanceof Admin a)         new AdminMenu(a, db).show();
            else if (user instanceof Manager m)  new ManagerMenu(m, db).show();
            else if (user instanceof Teacher t)  new TeacherMenu(t, db).show();
            else if (user instanceof Student s)  new StudentMenu(s, db).show();
        }
    }
}
