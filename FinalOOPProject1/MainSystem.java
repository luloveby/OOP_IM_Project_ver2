package FinalOOPProject1;

import java.util.*;

public class MainSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //create object and dito mag lagay ng default value si PRODUCT
        Product food = new Food();
        Product beverage = new Beverage();
        food.addDefaultValue();
        beverage.addDefaultValue();
        Suppliers sm =  new Suppliers();
        Ingredient ingre = new Ingredient();
        ingre.defaultIngredientupply();
        sm.addDefaultSuppliers();

        // Create admin and staff database
        HashMap<String, Admin> adminMap = new HashMap<>();
        HashMap<String, Staff> staffMap = new HashMap<>();

        // Admin account
        adminMap.put("admin", new Admin("admin", "admin123"));

        // Staff pre-made accounts
        staffMap.put("S001", new Staff("S001", "pass1", "Yenz", true));
        staffMap.put("S002", new Staff("S002", "pass2", "Shan", true));
        staffMap.put("S003", new Staff("S003", "pass3", "Wil", true));
        staffMap.put("S004", new Staff("S004", "pass4", "Lovely", true));
        staffMap.put("S005", new Staff("S005", "pass5", "Mai", true));

        Dashboard dashboard = new Dashboard(); 

        while (true) {
            System.out.println("\n========================= LOGIN PORTAL ===========================");
            System.out.println("[1] Admin Login");
            System.out.println("[2] Staff Sign-In / Login");
            System.out.println("[0] Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    Admin.login(sc, adminMap, dashboard, staffMap);
                    break;
                case "2":
                    Staff.handleStaffAccess(sc, staffMap, dashboard);
                    break;
                case "0":
                    System.out.println("System closed.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
