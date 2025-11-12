package FinalOOPProject1;

import java.util.*;

public class Staff extends User {
    private String name;
    private boolean firstLogin;

    public Staff(String id, String password, String name, boolean firstLogin) {
        super(id, password);
        this.name = name;
        this.firstLogin = firstLogin;
    }

    public String getName() { return name; }
    public boolean isFirstLogin() { return firstLogin; }
    public void setFirstLogin(boolean firstLogin) { this.firstLogin = firstLogin; }

    // Handles both sign-in (first time) and normal login
    public static void handleStaffAccess(Scanner sc, HashMap<String, Staff> staffMap, Dashboard dashboard) {
        System.out.print("Enter Staff ID: ");
        String id = sc.nextLine().toUpperCase();

        Staff s = staffMap.get(id);
        if (s == null) {
            System.out.println("Staff ID not found.");
            return;
        }

        System.out.print("Enter Password: ");
        String pw = sc.nextLine();

        if (s.isFirstLogin()) {
            if (s.password.equals(pw)) {
                System.out.println("First-time sign-in detected.");
                System.out.print("Enter new password: ");
                String newPass = sc.nextLine();
                s.setPassword(newPass);
                s.setFirstLogin(false);
                System.out.println("Password updated successfully. You can now log in normally.");
            } else {
                System.out.println("Incorrect temporary password.");
            }
            return;
        }

        if (s.password.equals(pw)) {
            System.out.println("Welcome, " + s.name + "!");
            dashboard.showStaffDashboard(sc, s);
        } else {
            System.out.println("Incorrect password.");
        }
    }

    public boolean checkPassword(String pw) {
        return this.password.equals(pw);
    }
}
