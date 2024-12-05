
# **BONUS LAB: Inventory Management System Using BufferedReader and BufferedWriter**




## **Lab Scenario**
You need to manage a store's inventory by:
- Storing product information (name, product ID, quantity, and price).
- Writing and reading product data from a text file.

---

## **Instructions**

### **1. Create the `Product` Class**
- Define a `Product` class with the following attributes:
  - `name` (String)
  - `productId` (String)
  - `quantity` (int)
  - `price` (double)
- Override the `toString()` method for easy display.

### **2. Writing Products to a File Using `BufferedWriter`**
- Implement a method `writeProductsToFile(List<Product> products, String filename)`:
  - Open the file using `BufferedWriter`.
  - Write each product's details line by line.
  - Close the file properly.

### **3. Reading Products from a File Using `BufferedReader`**
- Implement a method `readProductsFromFile(String filename)`:
  - Open the file using `BufferedReader`.
  - Read and display each line of product data.
  - Handle exceptions appropriately.

### **4. Main Program**
- In your `main` method:
  1. Create a list of sample products.
  2. Write the product list to a text file.
  3. Read and display the contents of the text file.

---

## **Bonus**
1. **Update Product Quantity:**  
   Implement a method to update the quantity of a product by its ID.
   
2. **Search Product by ID:**  
   Add a feature to search for a product by its product ID.
   
3. **Calculate Inventory Value:**  
   Create a method to calculate the total inventory value (`quantity * price`).
