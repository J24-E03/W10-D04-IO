import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductWR {

    public static void writeProductsToFile(List<Product> products, String filename) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) { //Open the file using BufferedWriter
            for (Product p : products) {
                //Write each product's details line by line.
                bufferedWriter.write(p.getName() + "," + p.getProductId() + "," + p.getQuantity() + "," + p.getPrice());
                bufferedWriter.newLine();
            }
            System.out.println("Product details written in the file");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void readProductsFromFile(String filename) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] details = line.split(",");
                String name = details[0]; //Read and display each line of product data.
                int productId = Integer.parseInt(details[1]);
                int quantity = Integer.parseInt(details[2]);
                double price = Double.parseDouble(details[3]);
                System.out.println("Products read from File: " + name + "," + productId + "," + quantity + "," + price);


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



