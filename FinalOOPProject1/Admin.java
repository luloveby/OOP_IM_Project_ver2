package FinalOOPProject1;

import java.util.*;
// nov 13
public class Admin extends User {

    public Admin(String id, String password) {
        super(id, password);
    }

    // Handles admin login
    public static void login(Scanner sc, HashMap<String, Admin> adminMap, Dashboard dashboard, HashMap<String, Staff> staffMap) {
        System.out.println("---------------------------------------------------------------");
        System.out.print("Enter Admin ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String pw = sc.nextLine();

        Admin a = adminMap.get(id);

        if (a != null && a.password.equals(pw)) {
            System.out.println("Login successful! Welcome, " + a.id);
            System.out.println("---------------------------------------------------------------");
            dashboard.showAdminDashboard(sc, a, staffMap);
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    public boolean checkPassword(String pw) {
        return this.password.equals(pw);
    }
}
