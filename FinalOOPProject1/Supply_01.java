package FinalOOPProject1;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Supply_01 {

    protected String code;
    protected String name;
    protected double price;
    protected String category;
    protected int stock;

    public abstract void addDefaultValue(String code, String name, double price, String category, int stock);

    public abstract void searchItem();

    public abstract void addInputSupply();

    public abstract void updateSupply();

    public abstract void deleteSupply();

    public abstract void showSupply();

    public void addStock(int quantity) {
        stock += quantity;
    }

    public void reduceStock(int quantity) {
        stock -= quantity;
    }

}

class Ingredient extends Supply_01 {

    Scanner s = new Scanner(System.in);
    private static ArrayList<Ingredient> ingreList = new ArrayList<>();
    public static String className = "Ingredient";

    public Ingredient() {
    }

    public Ingredient(String code, String name, double price, String category, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;

    }

    public static ArrayList<Ingredient> getIngreList() {
        return ingreList;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
    
    public int setStock(int newStock) {
        return this.stock = newStock;
    }

    @Override
    public void addDefaultValue(String code, String name, double price, String category, int stock) {
        Ingredient addIngre = new Ingredient(code, name, price, category, stock);
        ingreList.add(addIngre);
    }

    @Override
    public void searchItem() {
        System.out.print("Enter any ingredient's details to search: ");
        String target = s.nextLine().toUpperCase().trim();

        ArrayList<Ingredient> foundIngre = new ArrayList<>();

        for (Ingredient ingre : ingreList) {
            if (ingre.getCode().toUpperCase().contains(target)
                    || ingre.getName().toUpperCase().contains(target)
                    || ingre.getCategory().toUpperCase().contains(target)) {
                foundIngre.add(ingre);
            }
        }

        if (foundIngre.isEmpty()) {
            System.out.println("\nNo ingredient item found for: " + target);
            return;
        }

        System.out.println("\nRESULTS:");
        System.out.println("=====================================================================================");
        System.out.printf("%-10s %-25s %-13s %-15s %-15s",
                "CODE", "NAME", "PRICE", "STOCK", "CATEGORY");
        System.out.println("\n=====================================================================================");

        for (Ingredient item : foundIngre) {
            System.out.printf("%-10s %-25s Php. %-10.2f %-15d %-15s\n",
                    item.getCode(), item.getName(), item.getPrice(), item.getStock(), item.getCategory());

        }
    }

    @Override
    public void addInputSupply() {
        String inputCode;
        do {
            System.out.print("Enter Ingredient Code (5 Characters) : ");
            inputCode = s.next().toUpperCase().trim();
            s.nextLine();
        } while (inputCode.length() != 5);
        String code = inputCode;

        for (Ingredient ingre : ingreList) {
            if (ingre.code.equalsIgnoreCase(code)) {
                System.out.println("Ingredient code already exists!");
                return;
            }
        }

        System.out.print("Enter Ingredient Name: ");
        String name = s.nextLine().toUpperCase().trim();

        int stock;
        while (true) {
            try {
                System.out.print("Enter Stock: ");
                stock = s.nextInt();
                s.nextLine();

                if (stock < 0) {
                    System.out.println("Stock cannot be negative. Try again.");
                    continue;
                }
                
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine();
            }
        }

        System.out.print("Enter Category: ");
        String category = s.nextLine().toUpperCase().trim();

        double price;

        while (true) {
            try {
                System.out.print("Enter Ingredient Price: ");
                price = s.nextDouble();
                s.nextLine();

                if (price < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine();
            }
        }

        Ingredient newIngre = new Ingredient(code, name, price, category, stock);
        ingreList.add(newIngre);
        System.out.println("Ingredient added successfully..");
        System.out.println("---------------------------------------------------------------");
    }

    @Override
    public void updateSupply() {
        System.out.print("Enter Ingredient Code to Update: ");
        String codeToUpdate = s.next().toUpperCase().trim();

        for (Ingredient i : ingreList) {
            if (i.code.equalsIgnoreCase(codeToUpdate)) {
                System.out.println("\nItem's Current Info:");
                System.out.printf("Name: %s | Price: Php. %.2f | Category: %s%n", i.name, i.price, i.category);

                System.out.println("What do you want to update?");
                System.out.println("[C] Code");
                System.out.println("[N] Name");
                System.out.println("[P] Price");
                System.out.println("[S] Stock");
                System.out.println("[A] Category");

                System.out.print("Enter choice: ");
                String choice = s.next().toUpperCase().trim();

                if (choice.equals("C")) {
                    String inputCode;
                    do {
                        System.out.print("Enter new code (5 Characters): ");
                        inputCode = s.next().toUpperCase().trim();
                        s.nextLine();
                    } while (inputCode.length() != 5);
                    i.code = inputCode;
                    System.out.println("Ingredient code successfully updated");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("N")) {
                    System.out.print("Enter new Ingredient Name: ");
                    s.nextLine();
                    i.name = s.nextLine().toUpperCase().trim();
                    System.out.println("Ingredient name successfully updated");
                    System.out.println("---------------------------------------------------------------");
                } else if (choice.equals("P")) {
                    double price;

                    while (true) {
                        try {
                            System.out.print("Enter New Ingredient Price: ");
                            price = s.nextDouble();
                            s.nextLine();

                            if (price < 0) {
                                System.out.println("Price cannot be negative. Try again.");
                                continue;
                            }

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            s.nextLine();
                        }
                    }
                    i.price = price;

                    System.out.println("Ingredient price successfully updated");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("S")) {

                    int stock;

                    while (true) {
                        try {
                            System.out.print("Enter New Ingredient Stock: ");
                            stock = s.nextInt();
                            s.nextLine();

                            if (stock < 0) {
                                System.out.println("Stock cannot be negative. Try again.");
                                continue;
                            }

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            s.nextLine();
                        }
                    }
                    i.stock = stock;

                    System.out.println("Ingredient stock updated successfully");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("A")) {
                    System.out.print("Enter New Category: ");
                    i.category = s.nextLine().toUpperCase().trim();
                    System.out.println("Ingredient category successfully updated");
                    System.out.println("---------------------------------------------------------------");
                }

                return;
            }
        }
        System.out.println("Can't change the code. No Product is found with the code " + codeToUpdate);
    }

    @Override
    public void deleteSupply() {
        System.out.print("Enter Ingredient Code to Delete: ");
        String codeToDelete = s.next().toUpperCase().trim();

        Iterator<Ingredient> iterator = ingreList.iterator();
        while (iterator.hasNext()) {
            Ingredient item = iterator.next();
            if (item.code.equalsIgnoreCase(codeToDelete)) {
                System.out.print("Are you sure you want to delete " + codeToDelete + "? \n[Y] YES \t [N] NO: ");
                String confirmation = s.next().toUpperCase().trim();

                if (confirmation.equals("Y")) {
                    iterator.remove();
                    System.out.println("Product successfully deleted!");
                } else {
                    System.out.println("Deletion is cancelled...");
                }
                return;
            }
        }

        System.out.println("Can't delete. No Product is found with the code " + codeToDelete);
    }

    @Override
    public void showSupply() {
        if (ingreList.isEmpty()) {
            System.out.println("\nNo ingredient items available.\n");
            return;
        }

        int tableWidth = 85; // total width of the table including spaces
        String line = "=".repeat(tableWidth);

        System.out.println("\n=============================== INGREDIENT PRODUCTS =================================");
        System.out.printf("%-10s %-30s %-15s %-10s %-15s%n", "CODE", "NAME", "PRICE", "STOCK", "CATEGORY");
        System.out.println(line);

        for (Ingredient i : ingreList) {
            System.out.printf("%-10s %-30s Php. %-10.2f %-10d %-15s%n",
                    i.code, i.name, i.price, i.stock, i.category.toUpperCase());
        }
        System.out.println(line + "\n");
    }
    
    public void defaultIngredientupply(){
       
//        ingre.addDefaultValue("SP001", "Arabica Coffee Beans", 800.00, "INGREDIENT", 50);
//        ingre.addDefaultValue("SP002", "Milk", 90.00, "INGREDIENT", 100);
//        ingre.addDefaultValue("SP003", "Sugar", 60.00, "INGREDIENT", 200);
//        ingre.addDefaultValue("SP005", "Croffle Batter", 120.00, "INGREDIENT", 50);
//        ingre.addDefaultValue("SP006", "Whipped Cream", 110.00, "INGREDIENT", 40);
//        ingre.addDefaultValue("SP007", "Strawberry Syrup", 100.00, "INGREDIENT", 60);
//        ingre.addDefaultValue("SP009", "Burger Patty", 300.00, "INGREDIENT", 80);
//        ingre.addDefaultValue("SP010", "Rice", 60.00, "INGREDIENT", 200);
//        ingre.addDefaultValue("SP011", "Pasta Noodles", 90.00, "INGREDIENT", 100);
//        ingre.addDefaultValue("SP012", "Cheese", 75.00, "INGREDIENT", 150);

// ito yung new huhuh - hanami
        Ingredient ingre = new Ingredient();
        ingre.addDefaultValue("SP001", "Arabica Coffee Beans", 800.00, "INGREDIENT", 50); //0
        ingre.addDefaultValue("SP002", "Milk", 90.00, "INGREDIENT", 100);                   //1
        ingre.addDefaultValue("SP003", "Sugar", 60.00, "INGREDIENT", 200);
        ingre.addDefaultValue("SP005", "Croffle Batter", 120.00, "INGREDIENT", 50);
        ingre.addDefaultValue("SP006", "Whipped Cream", 110.00, "INGREDIENT", 40);
        ingre.addDefaultValue("SP007", "Chocolate Syrup", 100.00, "INGREDIENT", 60);
        ingre.addDefaultValue("SP008", "Pork", 120.00, "INGREDIENT", 60); // new pork -- wala sa IM 
        ingre.addDefaultValue("SP009", "Burger Patty", 300.00, "INGREDIENT", 80);
        ingre.addDefaultValue("SP010", "Rice", 60.00, "INGREDIENT", 200);
        ingre.addDefaultValue("SP011", "Pasta Noodles", 90.00, "INGREDIENT", 100);
        ingre.addDefaultValue("SP012", "Cheese", 75.00, "INGREDIENT", 150);

}

}

class Packaging extends Supply_01 {

    Scanner s = new Scanner(System.in);
    private static ArrayList<Packaging> packList = new ArrayList<>();
    public static String className = "Packaging";

    public Packaging() {
    }

    public Packaging(String code, String name, double price, String category, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
    }

    public static ArrayList<Packaging> getPackList() {
        return packList;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int setStock(int newStock) {
        return this.stock = newStock;
    }

    @Override
    public void addDefaultValue(String code, String name, double price, String category, int stock) {
        Packaging newPack = new Packaging(code, name, price, category, stock);
        packList.add(newPack);
    }

    @Override
    public void searchItem() {
        System.out.print("Enter any packaging details to search: ");
        String target = s.nextLine().toUpperCase().trim();

        ArrayList<Packaging> foundPack = new ArrayList<>();

        for (Packaging pack : packList) {
            if (pack.getCode().toUpperCase().contains(target)
                    || pack.getName().toUpperCase().contains(target)
                    || pack.getCategory().toUpperCase().contains(target)) {
                foundPack.add(pack);
            }
        }

        if (foundPack.isEmpty()) {
            System.out.println("\nNo packaging item found for: " + target);
            return;
        }

        System.out.println("\nRESULTS:");
        System.out.println("=====================================================================================");

        System.out.printf("%-10s %-25s %-13s %-15s %-15s",
                "CODE", "NAME", "PRICE", "STOCK", "CATEGORY");
        System.out.println("\n=====================================================================================");

        for (Packaging item : foundPack) {
            System.out.printf("%-10s %-25s Php. %-10.2f %-15d %-15s\n",
                    item.getCode(), item.getName(), item.getPrice(), item.getStock(), item.getCategory());
        }

    }

    @Override
    public void addInputSupply() {
        String inputCode;
        do {
            System.out.print("Enter Packaging Code (5 Characters) : ");
            inputCode = s.next().toUpperCase().trim();
            s.nextLine();
        } while (inputCode.length() != 5);
        String code = inputCode;

        for (Packaging pack : packList) {
            if (pack.code.equalsIgnoreCase(code)) {
                System.out.println("Packaging code already exists!");
                return;
            }
        }

        System.out.print("Enter Packaging Name: ");
        String name = s.nextLine().toUpperCase().trim();
        System.out.print("Enter Category: ");
        String category = s.nextLine().toUpperCase().trim();

        int stock;
        while (true) {
            try {
                System.out.print("Enter Available Stock: ");
                stock = s.nextInt();
                s.nextLine();

                if (stock < 0) {
                    System.out.println("Stock cannot be negative. Try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine();
            }
        }

        double price;
        while (true) {
            try {
                System.out.print("Enter Price: ");
                price = s.nextDouble();
                s.nextLine();

                if (price < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                s.nextLine();
            }
        }

        Packaging newPack = new Packaging(code, name, price, category, stock);
        packList.add(newPack);
        System.out.println("Packaging added successfully..");
        System.out.println("---------------------------------------------------------------");
    }

    @Override
    public void updateSupply() {
        System.out.print("Enter Packaging Code to Update: ");
        String codeToUpdate = s.next().toUpperCase().trim();

        for (Packaging i : packList) {
            if (i.code.equalsIgnoreCase(codeToUpdate)) {
                System.out.println("\nItem's Current Info:");
                System.out.printf("Name: %s | Price: Php. %.2f | Stock:  %d | Category: %s%n", i.name, i.price, i.stock, i.category);

                System.out.println("What do you want to update?");
                System.out.println("[C] Code");
                System.out.println("[N] Name");
                System.out.println("[P] Price");
                System.out.println("[S] Stock");
                System.out.println("[A] Category");
                System.out.print("Enter choice: ");
                String choice = s.next().toUpperCase().trim();

                if (choice.equals("C")) {
                    String inputCode;
                    do {
                        System.out.print("Enter new code (5 Characters): ");
                        inputCode = s.next().toUpperCase().trim();
                        s.nextLine();
                    } while (inputCode.length() != 5);
                    i.code = inputCode;
                    System.out.println("Packaging code successfully updated");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("N")) {
                    System.out.print("Enter new Packaging Name: ");
                    s.nextLine();
                    i.name = s.nextLine().toUpperCase().trim();
                    System.out.println("Packaging name successfully updated");
                    System.out.println("---------------------------------------------------------------");
                } else if (choice.equals("P")) {
                    double price;

                    while (true) {
                        try {
                            System.out.print("Enter New Price: ");
                            price = s.nextDouble();
                            s.nextLine();

                            if (price < 0) {
                                System.out.println("Price cannot be negative. Try again.");
                                continue;
                            }

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            s.nextLine();
                        }
                    }
                    i.price = price;
                    System.out.println("Price successfully updated");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("S")) {
                    int stock;
                    while (true) {
                        try {
                            System.out.print("Enter New Stock: ");
                            stock = s.nextInt();
                            s.nextLine();

                            if (stock < 0) {
                                System.out.println("Stock cannot be negative. Try again.");
                                continue;
                            }

                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            s.nextLine();
                        }
                    }
                    i.stock = stock;
                    System.out.println("Stock successfully updated");
                    System.out.println("---------------------------------------------------------------");

                } else if (choice.equals("A")) {
                    System.out.print("Enter New Category: ");
                    i.category = s.nextLine().toUpperCase().trim();
                    System.out.println("Category successfully updated");
                    System.out.println("---------------------------------------------------------------");
                }

                return;
            }
        }
        System.out.println("Can't change the code. No Product is found with the code " + codeToUpdate);
    }

    @Override
    public void deleteSupply() {
        System.out.print("Enter Packaging Code to Delete: ");
        String codeToDelete = s.next().toUpperCase().trim();

        Iterator<Packaging> iterator = packList.iterator();
        while (iterator.hasNext()) {
            Packaging item = iterator.next();
            if (item.code.equalsIgnoreCase(codeToDelete)) {
                System.out.print("Are you sure you want to delete " + codeToDelete + "? \n[Y] YES \t [N] NO: ");
                String confirmation = s.next().toUpperCase().trim();

                if (confirmation.equals("Y")) {
                    iterator.remove();
                    System.out.println("Product successfully deleted!");
                } else {
                    System.out.println("Deletion is cancelled...");
                }
                return;
            }
        }

        System.out.println("Can't delete. No Product is found with the code " + codeToDelete);
    }

    @Override
    public void showSupply() {
        if (packList.isEmpty()) {
            System.out.println("\nNo packaging items available.\n");
            return;
        }

        int tableWidth = 85;
        String line = "=".repeat(tableWidth);

        System.out.println("\n=============================== PACKAGING PRODUCTS ==================================");
        System.out.printf("%-10s %-30s %-15s %-10s %-15s%n", "CODE", "NAME", "PRICE", "STOCK", "CATEGORY");
        System.out.println(line);

        for (Packaging i : packList) {
            System.out.printf("%-10s %-30s Php. %-10.2f %-10d %-15s%n",
                    i.code, i.name, i.price, i.stock, i.category.toUpperCase());
        }
        System.out.println(line + "\n");
    }
    
    public void dafaultPackagingSupply(){
        Packaging pack = new Packaging();
        pack.addDefaultValue("SP004", "Cups", 150.00, "PACKAGING", 300);
        pack.addDefaultValue("SP008", "Plastic Straw", 20.00, "PACKAGING", 500);

}
}
    
/*
class tryRun {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
       
        Ingredient ingre = new Ingredient();
        Packaging pack = new Packaging();
        ingre.defaultIngredientupply();
        int choice = 0;
        do {
            try {

                System.out.println("===============================================================");
                System.out.println("                       CAFE SUPPLY VIEW                       ");
                System.out.println("===============================================================");
                System.out.printf("%-40s%s%n", "[1]  Add/Input Ingredients", "[6]  Add Packaging");
                System.out.printf("%-40s%s%n", "[2]  View Ingredients", "[7]  View Packaging");
                System.out.printf("%-40s%s%n", "[3]  Update Ingredients", "[8]  Update Packaging");
                System.out.printf("%-40s%s%n", "[4]  Search Ingredients", "[9]  Search Packaging");
                System.out.printf("%-40s%s%n", "[5]  Delete Ingredients", "[10] Delete Packaging");
                System.out.printf("%-40s%s%n", "[0]  Exit", "");
                System.out.println("===============================================================");

                System.out.print("Enter your choice: ");
                choice = s.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("Invalid Input...");
                s.nextLine();
                choice = -1;
            }
            switch (choice) {
                case 1:
                    ingre.addInputSupply();
                    break;
                case 2:
                    ingre.showSupply();
                    break;
                case 3:
                    ingre.updateSupply();
                    break;
                case 4:
                    ingre.searchItem();
                    break;
                case 5:
                    ingre.deleteSupply();
                    break;
                case 6:
                    pack.addInputSupply();
                    break;
                case 7:
                    pack.showSupply();
                    break;
                case 8:
                    pack.updateSupply();
                    break;
                case 9:
                    pack.searchItem();
                    break;
                case 10:
                    pack.deleteSupply();
                    break;
                case 0:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Try again.");
                    break;
            }

        } while (choice != 0);
    }
}
*/