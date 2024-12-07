import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainProgram {
    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();
        //Create a list of sample products.
        Product product1 = new Product("Chocolate", 101, 9, 20);
        Product product2 = new Product("Lays", 102, 3, 10);
        Product product3 = new Product("Soft Drink", 103, 4, 10);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        ProductWR.writeProductsToFile( products, "product.txt" );
        ProductWR.readProductsFromFile("product.txt");

    }
}

