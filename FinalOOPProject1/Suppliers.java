package FinalOOPProject1;
import java.util.*;

public class Suppliers {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Supplier> suppliers = new ArrayList<>();
        
    public static void supplierMain() {
        int choice;
        do {
            System.out.println();
            showSuppliers();
            System.out.println("\n======SUPPLIER MENU======");
            System.out.println("1. Add Supplier");
            System.out.println("2. Update Supplier");
            System.out.println("3. View Supplier Details");
            System.out.println("4. Manage Deliveries");
            //PWEDENG PALITAN NG BACK TO MAIN MENU OR WHAT
            System.out.println("5. Return to Admin Dashboard");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1: addSupplier();
                        break;
                case 2: updateSupplier();
                        break;
                case 3: viewDetails();
                        break;
                case 4: System.out.println("\n======DELIVERY MENU======");
                        System.out.println("1. Record Ingredient Delivery");
                        System.out.println("2. Record Packaging Delivery");
                        System.out.println("3. Show Delivery History");
                        System.out.println("4. Return to Supplier Menu");
                        System.out.print("Enter your choice: ");
                        int select = sc.nextInt();
                        SupplyDelivery sd = new SupplyDelivery();
                        switch (select) {
                            case 1: sd.recordIngredientDelivery();
                                    break;
                            case 2: sd.recordPackagingDelivery();
                                    break;
                            case 3: sd.showDeliveryHistory();
                                    break;
                            case 4: System.out.println("Returning to supplier menu...");
                                    return;
                                    //break;
                            default: System.out.println("Invalid action. Please try again.");
                        }
                        break;
                //PWEDENG PALITAN NG BACK TO MAIN MENU OR WHAT
                case 5: System.out.println("Returning to admin dashboard...");
                        return; // iniba ko - hanami
                        //break;
                default: System.out.println("Invalid action. Please try again.");
            } 
        }
        while (choice !=4);
    }
    
    
    static class Supplier {
        private String id;
        private String name;
        private String contactInfo;
        
        public Supplier(String id, String name, String contactInfo) {
            this.id = id;
            this.name = name;
            this.contactInfo = contactInfo;        
        }
        
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
         public String getContactInfo() {
            return contactInfo;
        }
        public void setContactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
        }
        
        public void showInfo() {
            System.out.println("========SUPPLIER INFO========");
            System.out.println("Supplier ID  : " + id);
            System.out.println("Supplier Name: " + name);
            System.out.println("Contact Info : " + contactInfo);
        } 
    }
    
    public static void showSuppliers() {
        System.out.println("======SUPPLIER LIST======");
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers yet.");
        } 
        else {
            for (int i = 0; i < suppliers.size(); i++) {
                System.out.println((i + 1) + ". " + suppliers.get(i).getName());
            }
       }
    }
    // by lovely
    public static Supplier getSupplier(int index) {
    if (index >= 0 && index < suppliers.size()) {
        return suppliers.get(index);
    }
    return null;
    }

    public static void addSupplier() {
        sc.nextLine();
        System.out.println("\n======NEW SUPPLIER======");
        String id;
        do {
           System.out.print("Enter Supplier ID  : ");
           id = sc.next().toUpperCase().trim();
           sc.nextLine();
       } while (id.length()!=5); 
        
        System.out.print("Enter Supplier Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact Info : ");
        String contact = sc.nextLine();
        
        suppliers.add(new Supplier(id, name, contact));
        System.out.println("Supplier added successfully!");
    }
    
     public static void updateSupplier() {
        if (suppliers.isEmpty()) {
            System.out.println("\nNo suppliers to update.");
            return;
        }
        System.out.print("\nEnter the number of the supplier to update: ");
        int index = sc.nextInt() - 1;
        sc.nextLine();
            if (index < 0 || index >= suppliers.size()) {
                System.out.println("Invalid supplier number.");
                return;
            }
        Supplier supplier = suppliers.get(index);
        System.out.println("\nUpdating supplier: " + supplier.getName());
        System.out.print("Enter new Supplier ID (leave blank to keep current): ");
        String id = sc.nextLine();
            if (!id.isEmpty()) supplier.setId(id);
        System.out.print("Enter new Supplier Name (leave blank to keep current): ");
        String name = sc.nextLine();
            if (!name.isEmpty()) supplier.setName(name);
        System.out.print("Enter new Contact Info (leave blank to keep current): ");
        String contact = sc.nextLine();
            if (!contact.isEmpty()) supplier.setContactInfo(contact);
        System.out.println("Supplier information updated successfully!");
     }
     
     public static void viewDetails() {
        if (suppliers.isEmpty()) {
            System.out.println("\nNo suppliers yet.");
            return;
        }
        System.out.print("\nEnter the number of the supplier to view details: ");
        int index = sc.nextInt() - 1;
        if (index >= 0 && index < suppliers.size()) {
            suppliers.get(index).showInfo();
        } else {
            System.out.println("Invalid supplier number.");
        }
    }
     
    public  void addDefaultSuppliers() {
        suppliers.add(new Supplier("SU001", "NDE Packaging ", "https://web.facebook.com/Nde.Packaging "));
        suppliers.add(new Supplier("SU002", "Sylver Restaurant and Cafe", " https://web.facebook.com/sylveropc")); 
        suppliers.add(new Supplier("SU003", "Nancy Store", "09685551234")); 
        suppliers.add(new Supplier("SU004", "J.De Guzman Store", "09653691953"));
    }
        
}