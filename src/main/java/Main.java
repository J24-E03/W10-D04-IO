import java.io.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("The new Book1", "tiger1", 400, true, true, LocalDate.now(), LocalDate.now(), LocalDate.now());
        Book book2 = new Book("The new Book2", "tiger2", 200, true, false, LocalDate.now(), LocalDate.now(), LocalDate.now());
        Book book3 = new Book("The new Book3", "tiger3", 500, false, true, LocalDate.now(), LocalDate.now(), LocalDate.now());
        ArrayList<Book> books = new ArrayList<>(List.of(book1, book2, book3));
        saveBooksToFile(books);
        readBookToFile();
    }

    public static void saveBooksToFile(ArrayList<Book> books) {
        System.out.println("Write to file method");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("library_books.txt"))) {
            for (Book book : books) {
                bufferedWriter.write(book.saveToDataBase());
            }
        } catch (IOException e) {
            System.out.println("Issue writing to file, please try again");
        }
    }

    public static void readBookToFile() {

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("library_books.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] book = line.split(",");
                String title = book[0];
                String author = book[1];
                int pages = Integer.parseInt(book[2]);
                boolean bestSeller = Boolean.parseBoolean(book[3]);
                boolean isAvailable = Boolean.parseBoolean(book[4]);
                LocalDate borrowDate = LocalDate.parse(book[5]); //parse the dates from the string
                LocalDate dueDate =LocalDate.parse(book[6]);
                LocalDate returnDate = LocalDate.parse(book[7]);
                System.out.println(title + "," + author + "," + pages + "," + bestSeller + "," +isAvailable + borrowDate + "," + dueDate + "," +returnDate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



