package FinalOOPProject1;

import java.util.*;
// nov 13
public class Dashboard {

    InventoryDashboard inventory = new InventoryDashboard();

    // Admin dashboard
    public void showAdminDashboard(Scanner sc, Admin admin, HashMap<String, Staff> staffMap) {
        OrderDetails details = new OrderDetails();
        while (true) {
            
            System.out.println("\n========================= ADMIN DASHBOARD ========================");
            System.out.println("[1] Manage Inventory");
            System.out.println("[2] Manage Orders");
            System.out.println("[3] Manage Suppliers");
            System.out.println("[4] Change Password");
            System.out.println("[0] Logout");
            System.out.print("Choice: ");
            int c = sc.nextInt();

            switch (c) {
                case 1:
                    inventory.manageInventory(sc);
                    break;
                case 2:
                    details.orderMenu();
                    break;
                    
                case 3:
                    Suppliers.supplierMain();
                    break;
                case 4:
                    System.out.print("Enter current password: ");
                    String currentPw = sc.nextLine();
                    if (admin.checkPassword(currentPw)) {
                        System.out.print("Enter new password: ");
                        String newPw = sc.nextLine();
                        admin.setPassword(newPw);
                        System.out.println("Password changed successfully.");
                    } else {
                        System.out.println("Incorrect password.");
                    }
                    break;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Staff dashboard
    public void showStaffDashboard(Scanner sc, Staff staff) {
        while (true) {
             System.out.println("\n========================= STAFF DASHBOARD ========================");
            System.out.println("[1] View Inventory");
            System.out.println("[2] Change Password");
            System.out.println("[0] Logout");
            System.out.print("Choice: ");
            String c = sc.nextLine();

            switch (c) {
                case "1":
                    inventory.viewInventory(sc);
                    break;
                case "2":
                    System.out.print("Enter current password: ");
                    String oldPw = sc.nextLine();
                    if (staff.checkPassword(oldPw)) {
                        System.out.print("Enter new password: ");
                        String newPw = sc.nextLine();
                        staff.setPassword(newPw);
                        System.out.println("Password changed successfully.");
                    } else {
                        System.out.println("Incorrect current password.");
                    }
                    break;
                case "0":
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
