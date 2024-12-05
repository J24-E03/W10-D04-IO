package inventory;
import java.nio.file.Path;
import java.util.Scanner;


public class Main {
    private static Path productsPath = Path.of("products.txt");
    private static Inventory inventorysystem;
    public static void main(String[] args) {
        InitializeInventorySystem();
        showMenuToAdmin();
    }

    private static void showMenuToAdmin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Inventory Management system!");

        boolean isRunning = true;
        do {
            System.out.println("Choose one option");
            System.out.println("1. View All Products");
            System.out.println("2. Update Product Quantity");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Calculate Inventory Value");
            System.out.println("5. Exit");
            System.out.println("Enter your choice:");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        inventorysystem.viewAllProducts();
                        break;
                    case 2:
                        System.out.println("Enter the product ID: ");
                        String id = scanner.nextLine();
                        System.out.println("Enter the new quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        inventorysystem.UpdateProductQuantity(id, quantity);
                        break;
                    case 3:
                        System.out.println("Enter the product ID: ");
                        inventorysystem.searchProduct(scanner.nextLine());
                        break;
                    case 4:
                        System.out.println("Inventory Value is: " + inventorysystem.calculateInventoryValue() + "$\n");
                        break;
                    case 5:
                        isRunning = false;
                        inventorysystem.writeProductsToFile();
                        System.out.println("Thank you and Goodbye!");
                        break;
                    default:
                        System.out.println("Please enter a valid choice (1–4).");
                        break;
                }

            } catch (Exception ex) {
                System.out.println("Invalid Input. Please try again!");
                isRunning = true;
            }

        } while (isRunning);

    }

    private static void InitializeInventorySystem() {
        inventorysystem = new Inventory(productsPath);
        inventorysystem.readProductsFromFile();
    }
}
