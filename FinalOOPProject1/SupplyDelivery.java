package FinalOOPProject1;
import java.time.LocalDateTime;
import java.util.*;

public class SupplyDelivery {

    private Suppliers suppliers;
    private Suppliers.Supplier supplier;
    private Ingredient ingredient;
    private int quantityDelivered;
    private Packaging packaging;
    private LocalDateTime dateTime;
    Scanner sc = new Scanner(System.in);
    
    private static final ArrayList<SupplyDelivery> deliveryHistory = new ArrayList<>();
    
    public SupplyDelivery(){}
    public SupplyDelivery(Suppliers.Supplier supplier, Ingredient ingredient,
                          int quantityDelivered, Packaging packaging, LocalDateTime dateTime) {
    this.supplier = supplier;
    this.ingredient = ingredient;
    this.quantityDelivered = quantityDelivered;
    this.packaging = packaging;
    this.dateTime = dateTime;
}

    
    public void recordIngredientDelivery() {
    
        Suppliers.showSuppliers();
        System.out.print("\nSelect Supplier Number: ");
        int supplierIndex = sc.nextInt() - 1;
        sc.nextLine();

        supplier = Suppliers.getSupplier(supplierIndex);
        if (supplier == null) {
            System.out.println("No supplier found.");
            return;
        }
        String ingCode;

        do {
            System.out.print("Enter the delivered ingredient code: ");
            ingCode = sc.nextLine().toUpperCase().trim();
        } while (ingCode.length() != 5);

        Ingredient selectedIngredient = null;
        Ingredient ing = new Ingredient();
        ing.defaultIngredientupply();
        for (Ingredient i : Ingredient.getIngreList()) {
            if (i.getCode().equalsIgnoreCase(ingCode)) {
                selectedIngredient = i;
                break;
            }
        }

        if (selectedIngredient==null) {
            System.out.println("Ingredient not found.");
            System.out.print("Do you want to ADD this as NEW Ingredient? [Y] Yes [N] No: ");
            char choice = sc.nextLine().toUpperCase().charAt(0);

            if (choice == 'Y') {
                Ingredient newIng = new Ingredient();
                newIng.addInputSupply();
                return;
            } else {
                System.out.println("Delivery cancelled.");
                return;
            }
        }
        System.out.print("Enter quantity delivered: ");
        quantityDelivered = sc.nextInt();
        sc.nextLine();

        if (quantityDelivered <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        selectedIngredient.setStock(selectedIngredient.getStock() + quantityDelivered);
        dateTime = LocalDateTime.now();  
        SupplyDelivery newDelivery = new SupplyDelivery(supplier, selectedIngredient, quantityDelivered, null, dateTime);
        deliveryHistory.add(newDelivery);

        showIngredientDeliveryDetails(selectedIngredient);
    }

    
    public void addDefaultIngredintRecord(Suppliers.Supplier supplier, Ingredient ingredient,
                          int quantityDelivered, Packaging packaging, LocalDateTime dateTime){
        SupplyDelivery newRecord = new SupplyDelivery (supplier, ingredient, quantityDelivered, packaging, dateTime);
        deliveryHistory.add(newRecord);
        
    }
    
    public void defaultIngredientDelivery(){
        SupplyDelivery newIngredient = new SupplyDelivery();
        newIngredient.addDefaultIngredintRecord(Suppliers.getSupplier(0), ingredient, quantityDelivered, packaging, dateTime);
    }
    
    public void recordPackagingDelivery() {

        Suppliers.showSuppliers();
        System.out.print("\nSelect Supplier Number: ");
        int supplierIndex = sc.nextInt() - 1;
        sc.nextLine();

        supplier = Suppliers.getSupplier(supplierIndex);
        if (supplier == null) {
            System.out.println("No supplier found.");
            return;
        }
        String packCode;

        do {
            System.out.print("Enter the delivered packaging code: ");
            packCode = sc.nextLine().toUpperCase().trim();
        } while (packCode.length() != 5);
        
        Packaging pack = new Packaging();
        pack.dafaultPackagingSupply();
        Packaging selectedPackaging = null;
        
        for (Packaging p : Packaging.getPackList()) {
            if (p.getCode().equalsIgnoreCase(packCode)) {
                selectedPackaging = p;
                break;
            }
        }

        if (selectedPackaging == null) {
            System.out.println("Packaging not found.");
            System.out.print("Do you want to ADD this as NEW Packaging? [Y] Yes [N] No: ");
            char choice = sc.nextLine().toUpperCase().charAt(0);

            if (choice == 'Y') {
                Packaging newPack = new Packaging();
                newPack.addInputSupply();
                return;
            } else {
                System.out.println("Delivery cancelled.");
                return;
            }
        }
        System.out.print("Enter quantity delivered: ");
        quantityDelivered = sc.nextInt();
        sc.nextLine();

        if (quantityDelivered <= 0) {
            System.out.println("Invalid quantity.");
            return;
        }

        selectedPackaging.setStock(selectedPackaging.getStock() + quantityDelivered);
        dateTime = LocalDateTime.now();  
        SupplyDelivery newDelivery = new SupplyDelivery(supplier, null, quantityDelivered, selectedPackaging, dateTime);
        deliveryHistory.add(newDelivery);


        showPackagingtDeliveryDetails(selectedPackaging);
        
    } 

    private void showIngredientDeliveryDetails(Ingredient ingre) {
        System.out.println("\n=================== DELIVERY RECORD ======================");
        System.out.println("Supplier      : " + supplier.getName());
        System.out.println("Ingredient    : " + ingre.getName());
        System.out.println("Qty Delivered : " + quantityDelivered);
        System.out.println("Updated Stock : " + ingre.getStock());
        System.out.println("Delivery Date : " + dateTime);
        System.out.println("==========================================================\n");
    }
    
    private void showPackagingtDeliveryDetails(Packaging pack) {
        System.out.println("\n=================== DELIVERY RECORD ======================");
        System.out.println("Supplier      : " + supplier.getName());
        System.out.println("Ingredient    : " + pack.getName());
        System.out.println("Qty Delivered : " + quantityDelivered);
        System.out.println("Updated Stock : " + pack.getStock());
        System.out.println("Delivery Date : " + dateTime);
        System.out.println("============================================================\n");
    }
    
    public void showDeliveryHistory() {
    if (deliveryHistory.isEmpty()) {
        System.out.println("No delivery history yet.");
        return;
    }

        System.out.println("\n================== DELIVERY HISTORY ===================");

    for (SupplyDelivery d : deliveryHistory) {
        System.out.println("Supplier      : " + d.supplier.getName());
        
        if (d.ingredient != null)
            System.out.println("Ingredient    : " + d.ingredient.getName());
        if (d.packaging != null)
            System.out.println("Packaging     : " + d.packaging.getName());
        
        System.out.println("Quantity      : " + d.quantityDelivered);
        System.out.println("Delivery Date : " + d.dateTime);
        System.out.println("========================================================\n");
    }
}

}

