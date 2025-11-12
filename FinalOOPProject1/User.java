package FinalOOPProject1;

// Parent class for Admin and Staff
public abstract class User {
    protected String id;
    protected String password;

    public User(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() { return id; }
    public String getPassword() { return password; }
    public void setPassword(String newPassword) { this.password = newPassword; }
}
