package FinalOOPProject1;

import java.util.*;

public class InventoryDashboard {

    // Admin inventory management
    public void manageInventory(Scanner sc) {
            
            
int choice =0;
        do {
            try {
            System.out.println("\n========================== INVENTORY SYSTEM ======================");
           
            System.out.println("[1] PRODUCT");
            System.out.println("[2] SUPPLY");
            System.out.println("[3] SUPPLY AND PRODUCT"); // recipe ata ito
            System.out.println("[0] BACK");
            System.out.println("==================================================================");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid Input...");   
                sc.nextLine(); 
                choice = -1;   
            }
            switch (choice) {
                case 1 : productMangement (sc); break; // nasa ibaba yung method decla nito
                case 2 : viewSupply(sc);break; //details.orderMenu();break;
                case 3 : viewRecipe(sc);
                case 0 :  return;
                default :  System.out.println("\nInvalid choice. Try again.");
            }
        } while (choice != 0);
    }
    
    

    // Staff inventory view
    public void viewInventory(Scanner sc) {
    
    Food food = new Food();
    Beverage beverage = new Beverage();
    OrderDetails details = new OrderDetails();

    int choice =0;
        do {
            try {
            
            System.out.println("\n========================== INVENTORY SYSTEM ======================");
            System.out.println("[1] VIEW PRODUCT");
            System.out.println("[2] TAKE ORDER");
            System.out.println("[3] SUPPLY AND PRODUCT"); // recipe ata ito
            System.out.println("[0] BACK");
            System.out.println("==================================================================");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            
            } catch (InputMismatchException e)
            {
                System.out.println("Invalid Input...");   
                sc.nextLine(); 
                choice = -1;   
            }
            switch (choice) {
                case 1 : food.showProducts();  
                         beverage.showProducts();break; 
                case 2 : details.orderMenu();break;
                case 3 :  viewRecipe(sc);
                case 0 :  return;
                default :  System.out.println("\nInvalid choice. Try again.");
            }
        } while (choice != 0);
    }
    
    // for managing the product//
    // for admin itoooo
    public void productMangement(Scanner sc){
        
      System.out.println("\n============================ PRODUCTS =============================");
      
        // show food nad beve
        Food food = new Food();
        Beverage beverage = new Beverage();
            // variable = (condition) ? valueIfTrue : valueIfFalse;

       food.showProducts();
       beverage.showProducts();
       System.out.println("[F]FOOD, [E] BEVERAGE, [B] <BACK");
       System.out.print("Select the type of product you would like to take action on:");
       String productType = sc.next().trim().toUpperCase();
       sc.nextLine();
       if(productType.equalsIgnoreCase("F"))
       {
            System.out.println("\n=========================== FOODS =====================================");
            System.out.println("[A] ADD NEW,  [S] SEARCH,  [U] UPDATE,  [D] DELETE,  [B] <BACK");
            System.out.print("Choose an action: ");
            String action = sc.next().trim().toUpperCase();
            switch(action)
             {
                  case "A": food.addInputProduct(); break;
                  case "S": food.searchItem(); break;
                  case "U": food.updateProduct(); break;
                  case "D": food.deleteProduct(); break;
                  case "B": return;
                  default: System.out.println("Invalid Action...");
            }
       }
       else if(productType.equalsIgnoreCase("E"))
       {
            System.out.println("\n ========================== BEVERAGES =================================");
            System.out.println("[A] ADD NEW,  [S] SEARCH,  [U] UPDATE,  [D] DELETE,  [B] <BACK");
            System.out.print("Choose an action: ");
            String action = sc.next().trim().toUpperCase();
            switch(action)
             {
                  case "A": beverage.addInputProduct(); break;
                  case "S": beverage.searchItem(); break;
                  case "U": beverage.updateProduct(); break;
                  case "D": beverage.deleteProduct(); break;
                  case "B": return;
                  default: System.out.println("Invalid Action...");
            }
       }
       else if(productType.equalsIgnoreCase("B"))
       {
           return;
       }
       else
       {
           
           System.out.println("\nInvalid input...");
           System.out.println("---------------------------------------------------------------");
       }
     }

    public void viewSupply(Scanner s){

    Ingredient ingre = new Ingredient();
    Packaging pack = new Packaging();
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

    public void viewRecipe(Scanner sc){
        System.out.println("\n ======================== RECIPE LIST ============================");
        System.out.println("[F] ADD NEW FOOD INGREDIENT  [E] ADD NEW BEVERAGE INGREDIENT [S] SHOW RECIPE LIST [B] <BACK");
        System.out.print("Choose an action: ");
        String action = sc.next().trim().toUpperCase();
        switch(action)
         {
              case "F": Recipe.addFoodRecipeList(); break;
              case "E": Recipe.addBeveRecipeList(); break; // iniba ko - hannah
              case "S": Recipe.showRecipeList(); break;
              case "B": return;
              default: System.out.println("Invalid Action...");
        }
    }


}