package FinalOOPProject1;

import java.util.*;
import java.io.*;
public abstract class Product {
    
    // protected na yung variables hereeeeeeeee, dati private e
    protected String code;
    protected String name;
    protected double price;
    protected String category;

    // Abstract CRUD methods
    
    public abstract void addDefaultValue();
    public abstract void searchItem();
    public abstract void addInputProduct();
    public abstract void updateProduct();
    public abstract void deleteProduct();
    public abstract void showProducts();
    public abstract void saveProductsToFile();
}

class Food extends Product {
    
    
    // dito ang data type is si food class
   private static ArrayList<Food> foodList = new ArrayList<>();
    public static String className = "Foods";
    Scanner in = new Scanner(System.in);
    
    // constructors here
    public Food() {}
    public Food(String code, String name, double price, String category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    // getter ni arraylist // hindi na static
      public static ArrayList<Food> getFoodList() {
        return foodList;
    }
      
  //  getters ni lang code, name...
      
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
  
   @Override
  public void addDefaultValue()
  {
        foodList.add(new Food("RM001", "Burger Steak", 80, "Rice Meal"));
        foodList.add(new Food("RM002", "Pork Sisig", 90, "Rice Meal")); // added
        foodList.add(new Food("CF001", "Classic Croffle", 98, "Croffle"));
        foodList.add(new Food("CF002", "Chocolate Croffle", 105, "Croffle"));
        foodList.add(new Food("HC001", "Ham & Cheese", 80, "Sandwich"));
  }
 @Override
   public void addInputProduct(){
       
       String inputCode;
       do {
           System.out.print("Enter Food Code (5 Characters): ");
           inputCode = in.next().toUpperCase().trim();
            in.nextLine();
            if (inputCode.length()!=5)
                           System.out.println("Please enter 5-Character Code..."); // new nov. 12
       } while (inputCode.length()!=5);
        
        
        String code = inputCode;

        for (Food item : foodList) {
            if (item.code.equalsIgnoreCase(code)) {
                System.out.println("Food code already exists!");
                return;
            }
        }

        System.out.print("Enter Food Name: ");
        String name = in.nextLine().toUpperCase().trim();
        System.out.print("Enter Category: ");
        String category = in.nextLine().toUpperCase().trim();
        
        double price;
       
        while (true) {
            try {
                System.out.print("Enter Food Price: ");
                price = in.nextDouble();

                if (price < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue; // go back to ask again
                }

                break; // valid input → exit loop
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.nextLine(); // clear invalid input from scanner buffer
            }
        }
       

    // ito ni-add si new Food object
        Food newFood = new Food(code, name, price, category);

    //then Add to the list
        foodList.add(newFood);
        System.out.println("Product successfully added..");
        System.out.println("---------------------------------------------------------------");
        saveProductsToFile();
   }
   
   @Override
 public void searchItem() {
    String tryAgain;
    do{
    System.out.print("Enter any food's details to search: ");
    String target = in.next().toUpperCase().trim();
    in.nextLine();
    
    ArrayList<Food> foundItems = new ArrayList<>();

    // Search in foodList
    for (Food food : foodList) {
        if (food.getCode().toUpperCase().contains(target) ||
            food.getName().toUpperCase().contains(target) ||
            food.getCategory().toUpperCase().contains(target)) {
            
            foundItems.add(food);
            
            
            // Display results

            System.out.println("\nRESULTS:");
            System.out.println("==================================================================");
            System.out.printf("%-10s %-25s %-13s %-15s\n", "CODE", "NAME", "PRICE", "CATEGORY");
            System.out.println("==================================================================");

            for (Food item : foundItems) {
                System.out.printf("%-10s %-25s Php. %-10.2f %-15s\n",
                item.getCode(), item.getName(), item.getPrice(), item.getCategory());
            }
        }
    }

    if (foundItems.isEmpty()) {
        System.out.println("\nNo food item found for: " + target);
    }
    
    System.out.println("\n---------------------------------------------------------------");
    System.out.println("Would you like to search again: ");
    System.out.println("[Y] YES [N] NO");
    System.out.print("Choose action: ");
    tryAgain = in.next().toUpperCase();
    } while(tryAgain.equalsIgnoreCase("Y"));
}

    @Override
   public void updateProduct() {
       
        System.out.print("Enter Food Code to Update: ");
        String codeToUpdate = in.next().toUpperCase().trim();

        for (Food f : foodList) {
            if (f.code.equalsIgnoreCase(codeToUpdate)) {
                System.out.println("\n==================================================================");
                System.out.println("Item's Current Info:");
                System.out.printf("Name: %s | Price: Php. %.2f | Category: %s%n", f.name, f.price, f.category);
                System.out.println("==================================================================");
                 
                System.out.println("What do you want to update?");
                System.out.println("[C] Code");
                System.out.println("[N] Name");
                System.out.println("[P] Price");
                System.out.println("[A] Category");
                System.out.println("[B] Back");
                System.out.print("Enter choice: ");
                String choice = in.next().toUpperCase().trim();

                if (choice.equals("C")) {
                    
                   String inputCode;
                   do {
                   System.out.print("Enter new code (5 Characters): ");
                    inputCode = in.next().toUpperCase().trim();
                    in.nextLine();
                    
                    if (inputCode.length()!=5)
                           System.out.println("Please enter 5-Character Code..."); // new nov. 12
                    } while (inputCode.length()!=5);
                   
                     f.code = inputCode;
                    System.out.println("Food code successfully updated");
                    System.out.println("---------------------------------------------------------------");
                    
                } else if (choice.equals("N")) {
                    System.out.print("Enter new Food Name: ");
                    in.nextLine();
                    f.name = in.nextLine().toUpperCase().trim();
                    System.out.println("Food name successfully updated");
                    System.out.println("---------------------------------------------------------------");
                } else if (choice.equals("P")) {
                    double price;
       
                    while (true) {
                        try {
                            System.out.print("Enter New Food Price: ");
                            price = in.nextDouble();

                            if (price < 0) {
                                System.out.println("Price cannot be negative. Try again.");
                                continue; // go back to ask again
                            }

                            break; // valid input ; exit loop
                        } 
                        catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            in.nextLine();
                        }
                    }
                    f.price = price;
                    
                    System.out.println("Food price successfully updated");
                    System.out.println("---------------------------------------------------------------");
                    
                } else if (choice.equals("A")) {
                    System.out.print("Enter New Category: ");
                    f.category = in.nextLine().toUpperCase().trim();
                      System.out.println("Food category successfully updated");
                    System.out.println("---------------------------------------------------------------");
                }
                
                else if(choice.equals("B")) // new nov. 12
                    return;
                return;
            }
        }

        System.out.println("Can't change the code. No Product is found with the code " + codeToUpdate);
    }
   
  @Override
public void deleteProduct() { // new nov.12
    while (true) {
        System.out.print("Enter Food Code to Delete: ");
        String codeToDelete = in.next().toUpperCase().trim();

        boolean found = false; // to track if a match is found

        Iterator<Food> iterator = foodList.iterator();
        while (iterator.hasNext()) {
            Food item = iterator.next();
            if (item.code.equalsIgnoreCase(codeToDelete)) {
                found = true; 
                System.out.println("Are you sure you want to delete " + codeToDelete + "?");
                System.out.println("[Y] YES [N] NO");
                System.out.print("Choose action: ");
                String confirmation = in.next().toUpperCase().trim();

                if (confirmation.equals("Y")) {
                    iterator.remove(); //  safe removal during iteration
                    System.out.println("Product successfully deleted!");
                } else {
                    System.out.println("Deletion is cancelled...");
                }
//                return; // exit method lang after handling one item
            }
        }

        if (!found) {
            System.out.println("Can't delete. No product found with the code " + codeToDelete);
        }

        // optional: ask user if they want to try again
        System.out.print("Do you want to try deleting another item? [Y/N]: ");
        String again = in.next().toUpperCase().trim();
        if (!again.equals("Y")) break;
    }
}

    
     @Override
    public void showProducts() {
        if (foodList.isEmpty()) {
            System.out.println("No food items available.");
            return;
        }
        
        System.out.println("\n============================= FOOD MENU ===========================");

        // unique categories
        ArrayList<String> categories = new ArrayList<>();
        for (Food item : foodList) {
            if (!categories.contains(item.category)) {
                categories.add(item.category);
            }
        }

        for (String category : categories) {
            System.out.println("\n~- " + category.toUpperCase() + " -~");
            System.out.printf("%-9s %-20s %-15s%n", "CODE", "NAME", "PRICE");
            for (Food item : foodList) {
                if (item.category.equalsIgnoreCase(category)) {
                    System.out.printf("%-9s %-20s Php. %.2f%n", item.code, item.name, item.price);
                }
            }
        }

       System.out.println("=====================================================================\n");
       
       saveProductsToFile();
    }
    
    @Override
    public void saveProductsToFile() {
    try {
        PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Hannah Mae Francisco\\Desktop\\ProductList.txt"));

        if (foodList.isEmpty()) {
            file.println("No food items available.");
            file.close();
            System.out.println("No food items available, nothing saved.");
            return;
        }
        
        file.println("\n============================= FOOD MENU ===========================");

        // Get unique categories
        ArrayList<String> categories = new ArrayList<>();
        for (Food item : foodList) {
            if (!categories.contains(item.category)) {
                categories.add(item.category);
            }
        }

        // Write grouped items by category
        for (String category : categories) {
            file.println("\n~- " + category.toUpperCase() + " -~");
            file.printf("%-9s %-20s %-15s%n", "CODE", "NAME", "PRICE");

            for (Food item : foodList) {
                if (item.category.equalsIgnoreCase(category)) {
                    file.printf("%-9s %-20s Php. %.2f%n", item.code, item.name, item.price);
                }
            }
        }

        file.println("=====================================================================");
        file.close();

        //System.out.println("Food list successfully saved to FoodList.txt");

    } catch (IOException e) {
        System.out.println("An error occurred while saving the food list to a file...");
    }
}

    
    
}

class Beverage extends Product {

    private static ArrayList<Beverage> beverageList = new ArrayList<>();
    public static String className = "Beverages";
    Scanner in = new Scanner(System.in);

    private double priceRegular;
    private double priceLarge;

    // Constructors
    public Beverage() {}
    public Beverage(String code, String name, double priceRegular, double priceLarge, String category) {
        this.code = code;
        this.name = name;
        this.priceRegular = priceRegular;
        this.priceLarge = priceLarge;
        this.category = category;
    }

    // Getter for list
    public static ArrayList<Beverage> getBeverageList() {
        return beverageList;
    }

    // Individual getters
    public String getCode() { return code; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPriceRegular() { return priceRegular; }
    public double getPriceLarge() { return priceLarge; }

    @Override
    public void addDefaultValue() {
        beverageList.add(new Beverage("PC001", "Americano", 90, 90, "Premium Coffee"));
        beverageList.add(new Beverage("PC002", "Spanish Latte", 99, 99, "Premium Coffee"));
        beverageList.add(new Beverage("MT001", "Wintermelon", 54, 65, "Milk Tea"));
        beverageList.add(new Beverage("MT002", "Dark Choco", 54, 65, "Milk Tea"));
        beverageList.add(new Beverage("PF001", "Rocky Road", 105, 105, "Frappe"));
        beverageList.add(new Beverage("SD001", "Strawberry", 80, 85, "Soda"));
        beverageList.add(new Beverage("SD002", "Blueberry", 80, 85, "Soda"));
    }

    @Override
    public void addInputProduct() {
        String inputCode;
        do {
            System.out.print("Enter Beverage Code (5 Characters): ");
            inputCode = in.next().toUpperCase().trim();
            if (inputCode.length() != 5)
                System.out.println("Please enter 5-Character Code..."); // ✅ new
        } while (inputCode.length() != 5);

        String code = inputCode;

        for (Beverage item : beverageList) {
            if (item.code.equalsIgnoreCase(code)) {
                System.out.println("Beverage code already exists!");
                return;
            }
        }

        System.out.print("Enter Beverage Name: ");
        in.nextLine(); 
        String name = in.nextLine().toUpperCase().trim();

        System.out.print("Enter Category: ");
        String category = in.nextLine().toUpperCase().trim();

        double priceRegular = 0;
        double priceLarge = 0;

        while (true) {
            try {
                System.out.print("Enter Regular Size Price: ");
                priceRegular = in.nextDouble();
                if (priceRegular < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }

                System.out.print("Enter Large Size Price: ");
                priceLarge = in.nextDouble();
                if (priceLarge < 0) {
                    System.out.println("Price cannot be negative. Try again.");
                    continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.nextLine(); 
            }
        }

        Beverage newBeverage = new Beverage(code, name, priceRegular, priceLarge, category);
        beverageList.add(newBeverage);
        System.out.println("Product successfully added..");
        System.out.println("---------------------------------------------------------------");
        saveProductsToFile();
    }

    @Override
    public void searchItem() {
        String tryAgain;
        do {
            System.out.print("Enter any beverage detail to search: ");
            String target = in.next().toUpperCase().trim();
            in.nextLine();

            ArrayList<Beverage> foundItems = new ArrayList<>();

            for (Beverage item : beverageList) {
                if (item.getCode().toUpperCase().contains(target) ||
                    item.getName().toUpperCase().contains(target) ||
                    item.getCategory().toUpperCase().contains(target)) {
                    foundItems.add(item);
                }
            }

            if (foundItems.isEmpty()) {
                System.out.println("\nNo beverage item found for: " + target);
            } else {
                System.out.println("\nRESULTS:");
                System.out.println("====================================================================================");
                System.out.printf("%-10s %-25s %-15s %-15s %-20s\n", "CODE", "NAME", "REGULAR", "LARGE", "CATEGORY");
                System.out.println("====================================================================================");
                for (Beverage item : foundItems) {
                    System.out.printf("%-10s %-25s Php. %-10.2f Php. %-10.2f %-15s\n",
                            item.getCode(), item.getName(), item.getPriceRegular(),
                            item.getPriceLarge(), item.getCategory());
                }
            }

            System.out.println("\n---------------------------------------------------------------");
            System.out.println("Would you like to search again?");
            System.out.println("[Y] YES [N] NO");
            System.out.print("Choose action: ");
            tryAgain = in.next().toUpperCase();
        } while (tryAgain.equalsIgnoreCase("Y"));
    }

    @Override
    public void updateProduct() {
        System.out.print("Enter Beverage Code to Update: ");
        String codeToUpdate = in.next().toUpperCase().trim();

        for (Beverage b : beverageList) {
            if (b.code.equalsIgnoreCase(codeToUpdate)) {
                System.out.println("\n==================================================================");
                System.out.println("Item's Current Info:");
                System.out.printf("Name: %s | Regular: Php. %.2f | Large: Php. %.2f | Category: %s%n",
                        b.name, b.priceRegular, b.priceLarge, b.category);
                System.out.println("==================================================================");

                System.out.println("What do you want to update?");
                System.out.println("[C] Code");
                System.out.println("[N] Name");
                System.out.println("[P] Price");
                System.out.println("[A] Category");
                System.out.println("[B] Back");
                System.out.print("Enter choice: ");
                String choice = in.next().toUpperCase().trim();

                if (choice.equals("C")) {
                    String inputCode;
                    do {
                        System.out.print("Enter new code (5 Characters): ");
                        inputCode = in.next().toUpperCase().trim();
                        if (inputCode.length() != 5)
                            System.out.println("Please enter 5-Character Code...");
                    } while (inputCode.length() != 5);
                    b.code = inputCode;
                    System.out.println("Beverage code successfully updated");
                } else if (choice.equals("N")) {
                    System.out.print("Enter new Beverage Name: ");
                    in.nextLine();
                    b.name = in.nextLine().toUpperCase().trim();
                    System.out.println("Beverage name successfully updated");
                } else if (choice.equals("P")) {
                    double priceRegular;
                    double priceLarge;

                    while (true) {
                        try {
                            System.out.print("Enter New Regular Size Price: ");
                            priceRegular = in.nextDouble();
                            if (priceRegular < 0) throw new InputMismatchException();

                            System.out.print("Enter New Large Size Price: ");
                            priceLarge = in.nextDouble();
                            if (priceLarge < 0) throw new InputMismatchException();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            in.nextLine();
                        }
                    }

                    b.priceRegular = priceRegular;
                    b.priceLarge = priceLarge;
                    System.out.println("Beverage price successfully updated");
                } else if (choice.equals("A")) {
                    System.out.print("Enter New Category: ");
                    in.nextLine();
                    b.category = in.nextLine().toUpperCase().trim();
                    System.out.println("Beverage category successfully updated");
                } else if (choice.equals("B")) {
                    return; // 
                }

                System.out.println("---------------------------------------------------------------");
                return;
            }
        }

        System.out.println("Can't update. No Beverage is found with the code " + codeToUpdate);
    }

    @Override
    public void deleteProduct() { 
        while (true) {
            System.out.print("Enter Beverage Code to Delete: ");
            String codeToDelete = in.next().toUpperCase().trim();

            boolean found = false;

            Iterator<Beverage> iterator = beverageList.iterator();
            while (iterator.hasNext()) {
                Beverage item = iterator.next();
                if (item.code.equalsIgnoreCase(codeToDelete)) {
                    found = true;
                    System.out.println("Are you sure you want to delete " + codeToDelete + "?");
                    System.out.println("[Y] YES [N] NO");
                    System.out.print("Choose action: ");
                    String confirmation = in.next().toUpperCase().trim();

                    if (confirmation.equals("Y")) {
                        iterator.remove();
                        System.out.println("Product successfully deleted!");
                    } else {
                        System.out.println("Deletion is cancelled...");
                    }
                }
            }

            if (!found) {
                System.out.println("Can't delete. No product found with the code " + codeToDelete);
            }

            System.out.print("Do you want to try deleting another item? [Y/N]: ");
            String again = in.next().toUpperCase().trim();
            if (!again.equals("Y")) break;
        }
    }

    @Override
    public void showProducts() {
        if (beverageList.isEmpty()) {
            System.out.println("No beverage items available.");
            return;
        }

        System.out.println("\n======================== BEVERAGE MENU ================================");
        ArrayList<String> categories = new ArrayList<>();

        for (Beverage item : beverageList) {
            if (!categories.contains(item.category)) {
                categories.add(item.category);
            }
        }

        for (String category : categories) {
            System.out.println("\n~- " + category.toUpperCase() + " -~");
            System.out.printf("%-9s %-20s %-15s %-15s%n", "CODE", "NAME", "REGULAR", "LARGE");
            for (Beverage item : beverageList) {
                if (item.category.equalsIgnoreCase(category)) {
                    System.out.printf("%-9s %-20s Php. %-10.2f Php. %-10.2f%n",
                            item.code, item.name, item.priceRegular, item.priceLarge);
                }
            }
        }

        System.out.println("=======================================================================\n");
        saveProductsToFile();
    }

    @Override
    public void saveProductsToFile() {
        try {
            PrintWriter file = new PrintWriter(new FileWriter("C:\\Users\\Hannah Mae Francisco\\Desktop\\ProductList.txt"));

            if (beverageList.isEmpty()) {
                file.println("No beverage items available.");
                file.close();
                System.out.println("No beverage items available, nothing saved.");
                return;
            }

            file.println("\n======================== BEVERAGE MENU ================================");
            ArrayList<String> categories = new ArrayList<>();

            for (Beverage item : beverageList) {
                if (!categories.contains(item.category)) {
                    categories.add(item.category);
                }
            }

            for (String category : categories) {
                file.println("\n~- " + category.toUpperCase() + " -~");
                file.printf("%-9s %-20s %-15s %-15s%n", "CODE", "NAME", "REGULAR", "LARGE");

                for (Beverage item : beverageList) {
                    if (item.category.equalsIgnoreCase(category)) {
                        file.printf("%-9s %-20s Php. %-10.2f Php. %-10.2f%n",
                                item.code, item.name, item.priceRegular, item.priceLarge);
                    }
                }
            }

            file.println("=======================================================================");
            file.close();

        } catch (IOException e) {
            System.out.println("An error occurred while saving the product list to a file...");
        }
    }

    public double getPriceBySize(String size) {
        if (size.equalsIgnoreCase("REGULAR")) {
            return this.priceRegular;
        } else if (size.equalsIgnoreCase("LARGE")) {
            return this.priceLarge;
        }
        return this.priceRegular;
    }
}
