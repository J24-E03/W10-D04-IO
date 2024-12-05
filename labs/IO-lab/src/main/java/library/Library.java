package library;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private static final int DUE_DATE = 14;
    private static final int NOTIFY_PERIOD = 3;
    private Path booksFilePath;
    private static List<Book> books = new ArrayList<>();

    public Library(Path booksFilePath) {
        this.booksFilePath = booksFilePath;
    }

    public Path getBooksFilePath() {
        return booksFilePath;
    }

    public void setBooksFilePath(Path booksFilePath) {
        this.booksFilePath = booksFilePath;
    }

    public void loadLibrary(){
        Book newBook;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(booksFilePath)))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] book = line.split(",");
                String title = book[0];
                String author = book[1];
                int numOfPages =Integer.parseInt(book[2]);
                boolean isBestseller = Boolean.parseBoolean(book[3]);
                boolean isAvailable = Boolean.parseBoolean(book[4]);
                LocalDate borrowDate, dueDate, returnDate;


                newBook = new Book(title, author, numOfPages, isBestseller, isAvailable);
                if (!book[5].equalsIgnoreCase("null")) {
                    borrowDate = LocalDate.parse(book[5]);
                    newBook.setBorrowDate(borrowDate);
                } else {
                    newBook.setBorrowDate(null);
                }
                if (!book[6].equalsIgnoreCase("null")) {
                    dueDate = LocalDate.parse(book[6]);
                    newBook.setDueDate(dueDate);
                } else {
                    newBook.setDueDate(null);
                }
                if (!book[7].equalsIgnoreCase("null")) {
                    returnDate = LocalDate.parse(book[7]);
                    newBook.setReturnDate(returnDate);
                } else {
                    newBook.setReturnDate(null);
                }
                books.add(newBook);
            }
        }
        catch (IOException ex) {
            System.out.println("Issue reading from the file!");
        }
    }

    public void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        books.stream()
                .filter(Book::isAvailable)
                .forEach(System.out::println);
    }

    public void viewAllBooks() {
        System.out.println("\nAll Books in the Library:");
        books.forEach(System.out::println);
    }

    public void viewBestSellerBooks() {
        System.out.println("\nBestseller Books:");
        books.stream()
                .filter(Book::isBestSeller)
                .forEach(System.out::println);
    }


   public void searchBookByAuthor(String author) {
        System.out.println("\nBooks by " + author + ":");
        boolean found = false;
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                found = true;
                System.out.println(book);
            }
        }
        if (! found) {
            System.out.println("No books found by this author.");
        }
    }

    public void borrowBookByTitle(String title) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    LocalDate borrowDate = LocalDate.now();
                    LocalDate dueDate = borrowDate.plusDays(DUE_DATE);
                    book.setBorrowDate(borrowDate);
                    book.setDueDate(dueDate);
                    System.out.println("You have Checked out " + book.getTitle() + " on " + borrowDate);
                    System.out.println("The Due Date is: " + dueDate);
                    return;
                }
                System.out.println("Sorry, the book " + book.getTitle() + " is currently unavailable");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    public void returnBook(String title) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isAvailable()) {
                    LocalDate currentDate = LocalDate.now();
                    book.setAvailable(true);
                    book.setReturnDate(currentDate);
                    if (book.getDueDate() != null && book.getDueDate().isBefore(currentDate)) {
                        System.out.println("The book is overdue! your fine will be " + ChronoUnit.DAYS.between(book.getDueDate(), currentDate) + "$.");
                    }
                    System.out.println("You have successfully returned " + book.getTitle() + ". Thank you!");
                    return;
                }
                System.out.println("The book " + book.getTitle() + " is already available in the library.");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    public void checkOverdueBooks() {
        System.out.println("\nList of all overdue Books:");
        LocalDate currentDate = LocalDate.now();
        for (Book book : books) {
            if (book.getReturnDate() == null && book.getDueDate() != null && book.getDueDate().isBefore(currentDate)) {
                System.out.println(book + " overdue for " + ChronoUnit.DAYS.between(book.getDueDate(), currentDate) + " days.");
            }
        }
    }

    public void extendDueDate(String title, int additionalDays) {
        if (title.isBlank()) {
            System.out.println("Invalid input. Book title cannot be empty.");
            return;
        }
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (!book.isAvailable() && book.getDueDate() != null) {
                    System.out.println("Due Date of this book is: " + book.getDueDate());
                    book.setDueDate(book.getDueDate().plusDays(additionalDays));
                    System.out.println("New Due Date will be: " + book.getDueDate());
                    return;
                }
                System.out.println("The book " + book.getTitle() + "  isn't borrowed! You cannot change any Due date for this book.");
                return;
            }
        }
        System.out.println("The book " + title + " was not found in our records.");
    }

    public void filterBooksByBorrowDate(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            System.out.println("Dates cannot be null.");
            return;
        }

        if (startDate.isAfter(endDate)) {
            System.out.println("Invalid range.");
            return;
        }

        System.out.println("All books borrowed between " + startDate + " and " + endDate + ":");
        for (Book book : books) {
            if (book.getBorrowDate() != null && book.getBorrowDate().isAfter(startDate) && book.getBorrowDate().isBefore(endDate)) {
                System.out.println(book);
            }
        }
    }

    public void notifyDueBooks() {
        System.out.println("These books should be returned within the next three days:");
        boolean found = false;
        for (Book book : books) {
            if (!book.isAvailable() && book.getDueDate() != null && book.getDueDate().isAfter(LocalDate.now())) {
                if (ChronoUnit.DAYS.between(LocalDate.now(), book.getDueDate()) <= NOTIFY_PERIOD) {
                    found = true;
                    System.out.println(book + " hast DueDate on: " + book.getDueDate());
                }
            }
        }
        if (!found) {
            System.out.println("No Book was found!");
        }
    }

    public void saveBooksToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(booksFilePath)))) {
            for (Book book : books) {
                bufferedWriter.write(book.serialize());
                bufferedWriter.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Issue writing into file.");
        }
    }
}
