
package Supply;
import java.util.*;


public class Recipe {

    private Product product;
    private Map<Ingredient, Integer> ingredients = new HashMap<>();
    private static ArrayList<Recipe> recipeList = new ArrayList<>();

    public Recipe (){}
    public Recipe(Product product) {
        this.product = product;
    }

    public void addIngredient(Ingredient ingredient, int qtyNeeded) {
        ingredients.put(ingredient, qtyNeeded);
    }
    
    public static ArrayList<Recipe> getRecipeList() {
    return recipeList;
}


    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public Product getProduct() {
        return product;
    }
    

    public void addRecipe(Recipe r) {
        recipeList.add(r);
    }

    private Food findFoodByCode(String code) {
        for (Food f : Food.getFoodList()) {
            if (f.getCode().equalsIgnoreCase(code)) return f;
        }
        return null;
    }

    private Beverage findBeverageByCode(String code) {
        for (Beverage b : Beverage.getBeverageList()) {
            if (b.getCode().equalsIgnoreCase(code)) return b;
        }
        return null;
    }

    public static Recipe getRecipeByProduct(Product product) {
    for (Recipe r : recipeList) {
        if (r.getProduct().equals(product)) return r;
    }
    return null;
    
}
   public void addFoodRecipeList() {
    Scanner s = new Scanner(System.in);
    String inputFoodCode;

    do {
        System.out.print("Enter Food Code (5 characters): ");
        inputFoodCode = s.nextLine().toUpperCase().trim();
    } while (inputFoodCode.length() != 5);

    Food selectedFood = null;
    for (Food f : Food.getFoodList()) {
        if (f.getCode().equalsIgnoreCase(inputFoodCode)) {
            selectedFood = f;
            break;
        }
    }
    if (selectedFood == null) {
        System.out.println("Food code not found.");
        return;
    }

    // iniba ko   if (r.getProduct().Code().equalsIgnoreCase(inputBeveCode)) {
    Recipe recipe = null;
    for (Recipe r : Recipe.getRecipeList()) {
        if (r.getProduct().code.equalsIgnoreCase(inputFoodCode)) {
            recipe = r;
            break;    
        }
    }

    if (recipe != null) {
        System.out.print("Recipe already exists. Add ingredient? [Y/N]: ");
        char choice = s.nextLine().toUpperCase().charAt(0);

        if (choice != 'Y') {
            System.out.println("Returning to menu...\n");
            return;
        }
    } else {
        recipe = new Recipe(selectedFood);
        recipe.addRecipe(recipe);
        System.out.println("New recipe created.");
    }

    char addMore;
    do {
        System.out.print("Enter Ingredient Code (5 characters): ");
        String ingCode = s.nextLine().toUpperCase().trim();

        Ingredient selectedIngredient = null;
        for (Ingredient ing : Ingredient.getIngreList()) {
            if (ing.getCode().equalsIgnoreCase(ingCode)) {
                selectedIngredient = ing;
                break;
            }
        }

        if (selectedIngredient == null) {
            System.out.println("Ingredient code not found.");
        } else {
            System.out.print("Enter quantity needed: ");
            int qty = s.nextInt();
            s.nextLine(); // consume leftover newline

            recipe.addIngredient(selectedIngredient, qty);
            System.out.println("Ingredient/s added");
        }

        System.out.print("Add another ingredient? [Y/N]: ");
        addMore = s.nextLine().toUpperCase().charAt(0);

    } while (addMore == 'Y');

    System.out.println("\nRecipe successfully updated for: " + selectedFood.getName() + "\n");
}


   
public void addBeveRecipeList() {
    Scanner s = new Scanner(System.in);
    String inputBeveCode;

    do {
        System.out.print("Enter Beverage Code (5 Characters): ");
        inputBeveCode = s.next().toUpperCase().trim();
        s.nextLine(); 
    } while (inputBeveCode.length() != 5);
    
    Beverage selectedDrink = null;
    for (Beverage d : Beverage.getBeverageList()) {
        if (d.getCode().equalsIgnoreCase(inputBeveCode)) {
            selectedDrink = d;
            break;
        }
    }

    if (selectedDrink == null) {
        System.out.println("Beverage code not found.");
        return;
    }
    
    // iniba ko yung   if (r.getProduct().Code().equalsIgnoreCase(inputBeveCode)) {
    Recipe recipe = null;
    for (Recipe r : Recipe.getRecipeList()) {
        if (r.getProduct().code.equalsIgnoreCase(inputBeveCode)){
            recipe = r;
            break;
        }
    }

    if (recipe != null) {
        System.out.print("Recipe already exists. Add ingredient? [Y] Yes [N] No: ");
        char choice = s.nextLine().toUpperCase().charAt(0);

        if (choice != 'Y') {
            System.out.println("Returning to menu...\n");
            return;
        }
    } else {
        recipe = new Recipe(selectedDrink);
        recipe.addRecipe(recipe);
        System.out.println("New recipe created.");
    }

    char addMore;
    do {
        System.out.print("Enter Ingredient Code (5 Characters): ");
        String ingCode = s.nextLine().toUpperCase().trim();

        Ingredient selectedIngredient = null;
        for (Ingredient ing : Ingredient.getIngreList()) {
            if (ing.getCode().equalsIgnoreCase(ingCode)) {
                selectedIngredient = ing;
                break;
            }
        }

        if (selectedIngredient == null) {
            System.out.println("Ingredient code not found.");
        } else {
            System.out.print("Enter quantity needed: ");
            int qty = s.nextInt();
            s.nextLine();

            recipe.addIngredient(selectedIngredient, qty);
            System.out.println("Ingredient added.");
        }

        System.out.print("Add another ingredient? [Y] Yes [N] No: ");
        addMore = s.nextLine().toUpperCase().charAt(0);

    } while (addMore == 'Y');

    System.out.println("Recipe updated for: " + selectedDrink.getName());
}

   public void showRecipeList() {
        if (recipeList.isEmpty()) {
                System.out.println("No recipes available.");
                return;
            }
                    System.out.println("======================RECIPE LIST=====================");
            for (Recipe r : recipeList) {

                //System.out.println("Recipe for: " + r.getProduct().getName());

                System.out.println("Recipe for: " + r.getProduct().name);
                for (Map.Entry<Ingredient, Integer> entry : r.getIngredients().entrySet()) {
                    System.out.println(" - " + entry.getKey().getName() + ": " + entry.getValue());
                }
            }    
   }

}


