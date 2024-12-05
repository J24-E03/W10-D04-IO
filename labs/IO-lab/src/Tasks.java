import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Tasks {
    private static final Path booksFilePath = Path.of("library_books.txt");
    private static final ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        if (Files.exists(booksFilePath)) {
            loadData();
        } else {
            initializeBooks();
        }

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            displayMenu();
            System.out.print("Please select an option (1-4): ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    viewAvailableBooks();
                    break;
                case 2:
                    System.out.print("Enter the book title: ");
                    borrowBookByTitle(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter the book title: ");
                    returnBook(scanner.nextLine());
                    break;
                case 4:
                    scanner.close();
                    saveBooksToFile();
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        } while (true);
    }

    private static void displayMenu() {
        System.out.println("""
                
                1. View Available Books
                2. Borrow a Book
                3. Return a book
                4. Exit""");
    }

    private static void initializeBooks() {
        books.add(new Book("James", 690, "Percival Everett", false, true));
        books.add(new Book("Martyr!", 1023, "Kaveh Akbar", true, true));
        books.add(new Book("Funny Story", 876, "Salma Rushdie", false, true));
        books.add(new Book("Colored Television", 1034, "Danzy Senna", true, true));
        books.add(new Book("Rocket man", 1531, "Tom Freiburg", true, true));
    }

    private static void viewAvailableBooks() {
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    private static void borrowBookByTitle(String title) {
        Book book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            book.setBorrowDate(LocalDate.now());
            book.setDueDate(LocalDate.now().plusDays(14));
            System.out.printf("You have checked out %s (borrow date: %s, due date: %s)", book.getTitle(), book.getBorrowDate(), book.getDueDate());
            return;
        }
        System.out.println("Sorry, the book " + title + " is currently unavailable.");
    }

    private static void returnBook(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.setAvailable(true);
            book.setReturnDate(LocalDate.now());
            System.out.println("You have successfully returned the book. Thank you!");

            if (book.getDueDate() != null) {
                long overdueValue = ChronoUnit.DAYS.between(LocalDate.now(), book.getDueDate());
                if (book.getDueDate().isBefore(LocalDate.now())) {
                    System.out.printf("Overdue price: %d$", overdueValue);
                }
            }
        } else {
            System.out.println("The book " + title + " was not found in our records.");
        }
    }

    private static Book findBookByTitle(String title) {
        return books.stream().filter(book -> title.equals(book.getTitle())).findFirst().orElse(null);
    }

    private static Book findBookByAuthor(String author) {
        return books.stream().filter(book -> author.equals(book.getAuthor())).findFirst().orElse(null);
    }

    private static void displayAllBooks() {
        System.out.println(books);
    }

    private static void filterBooks() {
        System.out.println(books.stream().filter(Book::isBestSeller).toList());
    }

    private static void checkOverdueBooks() {
        for (Book book : books) {
            if (book.getDueDate().isBefore(LocalDate.now()) && book.getReturnDate() == null) {
                System.out.println(book.getTitle() + ": " + ChronoUnit.DAYS.between(book.getDueDate(), LocalDate.now()));
            }
        }
    }

    private static void extendDueDate(String title, int additionalDays) {
        Book book = findBookByTitle(title);
        if (book != null) {
            book.setDueDate(book.getDueDate().plusDays(additionalDays));
            System.out.println("The new due date is: " + book.getDueDate());
        }
    }

    private static void filterBooksByBorrowDate(LocalDate startDate, LocalDate endDate) {
        for (Book book : books) {
            if (book.getBorrowDate().isAfter(startDate) && book.getBorrowDate().isBefore(endDate)) {
                System.out.println(book);
            }
        }
    }

    private static void notifyDueBooks() {
        for (Book book : books) {
            if (book.getDueDate().isBefore(LocalDate.now().plusDays(3))) {
                System.out.println(book);
            }
        }
    }

    private static void saveBooksToFile() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(booksFilePath)) {
            for (Book book : books) {
                writer.write(book.saveToDatabase());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void loadData() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(booksFilePath)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] book = line.split(",");
                Book item = new Book(book[0], Integer.parseInt(book[1]), book[2], Boolean.parseBoolean(book[3]), Boolean.parseBoolean(book[4]));
                if (!book[5].equals("null")) {
                    item.setReturnDate(LocalDate.parse(book[5]));
                } else {
                    item.setReturnDate(null);
                }
                if (!book[6].equals("null")) {
                    item.setReturnDate(LocalDate.parse(book[6]));
                } else {
                    item.setReturnDate(null);
                }
                if (!book[7].equals("null")) {
                    item.setReturnDate(LocalDate.parse(book[7]));
                } else {
                    item.setReturnDate(null);
                }
                books.add(item);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
