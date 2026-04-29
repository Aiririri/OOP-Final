package university.model;

public abstract class User implements Comparable<User> {

    private static int nextId = 1;

    private final int    id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String firstName, String lastName, String email, String password) {
        this.id        = nextId++;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.password  = password;
    }

    public int    getId()        { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getFullName()  { return firstName + " " + lastName; }
    public String getEmail()     { return email; }

    public void setFirstName(String v) { firstName = v; }
    public void setLastName(String v)  { lastName  = v; }
    public void setEmail(String v)     { email     = v; }
    public void setPassword(String v)  { password  = v; }

    public boolean checkPassword(String p) { return password.equals(p); }

    public abstract String getRole();

    @Override public int compareTo(User o) {
        return getFullName().compareToIgnoreCase(o.getFullName());
    }

    @Override public String toString() {
        return String.format("[%d] %-25s %-30s %s", id, getFullName(), email, getRole());
    }
}
