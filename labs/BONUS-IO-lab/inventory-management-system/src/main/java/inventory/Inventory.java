package inventory;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static List<Product> products = new ArrayList<>();
    private final Path productsPath;
    public Inventory (Path productsPath) {
        this.productsPath = productsPath;
    }

    public static List<Product> getProducts() {
        return products;
    }

    public static void setProducts(List<Product> products) {
        Inventory.products = products;
    }

    public void writeProductsToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(productsPath)))) {
            for (Product product : products) {
                bufferedWriter.write(product.serialize());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Issue while writing into file.");
        }
    }

    public void readProductsFromFile() {
        products.clear();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(productsPath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] product = line.split(",");
                if (product.length == 4) {
                    String name = product[0];
                    String id = product[1];
                    int quantity = Integer.parseInt(product[2]);
                    double price = Double.parseDouble(product[3]);
                    products.add(new Product(name, id, price, quantity));
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Issue reading from file.");
        }
    }

    public void UpdateProductQuantity(String id, int quantity) {
        if (id.isBlank()) {
            System.out.println("Invalid ID.");
            return;
        }
        if (quantity <= 0){
            System.out.println("Invalid quantity.");
            return;
        }

        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(id)) {
                product.setQuantity(quantity);
                System.out.println("Quantity updated successfully.");
                return;
            }
        }
        System.out.println("Product Not found!");
    }

    public void searchProduct(String id) {
        if (id.isBlank()){
            System.out.println("Invalid ID.");
            return;
        }
        for (Product product : products) {
            if (product.getProductId().equalsIgnoreCase(id)) {
                System.out.println(product);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public double calculateInventoryValue() {
        double inventoryValue = 0.0;
        for (Product product : products) {
            inventoryValue += product.getQuantity() * product.getPrice();
        }
        return inventoryValue;
    }

    public void viewAllProducts () {
        System.out.println("List of all Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
